package com.brandwatch.ivanatwitterapp.mentionsmatcher.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.Query;
import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;
import com.brandwatch.ivanatwitterapp.mentionsmatcher.kafka.MentionProducer;

@Service
public class LuceneService {

    private final Directory memoryIndex;
    private final StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
    private final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
    private final IndexWriter writer;
    private final QueryRepository queryRepository;
    private final MentionProducer mentionProducer;
    private ConcurrentLinkedDeque<Resource> resourceQueue = new ConcurrentLinkedDeque<>();
    private static final Logger log = LoggerFactory.getLogger(LuceneService.class);

    @Autowired
    public LuceneService(QueryRepository queryRepository, MentionProducer mentionProducer) throws IOException {
        this.mentionProducer = mentionProducer;
        this.queryRepository = queryRepository;
        memoryIndex = new MMapDirectory(getTempDirectory());
        writer = new IndexWriter(memoryIndex, indexWriterConfig);
    }

    public void addResourceInQueue(Resource resource) {
        log.info("resource added to queue");
        resourceQueue.add(resource);
    }

    @Scheduled(fixedDelay = 5000)
    public void addResourcesToIndex() throws IOException, ParseException {

        log.info("Adding resources to search index...");
        while (!resourceQueue.isEmpty()) {
            Resource resource = resourceQueue.poll();
            Document document = new Document();
            document.add(new StoredField("resourceId", resource.getResourceId()));
            document.add(new TextField("text", resource.getText(), Field.Store.YES));
            document.add(new TextField("author", resource.getAuthor(), Field.Store.YES));
            document.add(new TextField("source", resource.getSource(), Field.Store.YES));
            document.add(new TextField("createdAt", resource.getCreatedAt().toString(), Field.Store.YES));
            document.add(new TextField("source", resource.getSource(), Field.Store.YES));
            writer.addDocument(document);
        }

        writer.commit();
        searchIndex();
    }

    private void searchIndex() throws IOException, ParseException {
        log.info("Search index is called...");
        List<Query> allQueries = queryRepository.readQueries();
        IndexReader indexReader = DirectoryReader.open(memoryIndex);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        for (Query userQuery : allQueries) {
            org.apache.lucene.search.Query luceneQuery = new QueryParser("text", standardAnalyzer)
                    .parse(userQuery.getQueryDefinition());
            TopDocs topDocs = indexSearcher.search(luceneQuery, 10);
            log.info("Total number of docs matched: ", topDocs.totalHits.value);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                mentionProducer.send(toMention(document, userQuery.getId()));
            }
        }
        writer.deleteAll();
        writer.commit();
    }

    private Mention toMention(Document doc, long queryId) {
        return new Mention.Builder()
                .withQueryId(queryId)
                .withText(doc.get("text"))
                .withSource(doc.get("source"))
                .withMentionId(doc.get("resourceId") + "!" + queryId)
                .fromUser(doc.get("author"))
                .createdAt(Instant.parse(doc.get("createdAt")))
                .build();
    }

    private Path getTempDirectory() throws IOException {
        return Files.createTempDirectory(LuceneService.class.getSimpleName());
    }
}
