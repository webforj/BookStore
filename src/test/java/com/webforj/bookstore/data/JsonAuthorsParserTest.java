package com.webforj.bookstore.data;

import com.googlecode.cqengine.persistence.support.serialization.KryoSerializer;
import com.webforj.bookstore.repository.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class JsonAuthorsParserTest {

    @Value("classpath:db/authors.json")
    Resource authorsResource;

    private JsonAuthorsParser jsonAuthorsParser;

    @BeforeEach
    void setUp() {
        this.jsonAuthorsParser = new JsonAuthorsParser();
    }

    @AfterEach
    void tearDown() {
    }
    //
    //    @Test
    //    void developFormat() {
    //        Date dateofbirth = Date.from(Instant.now().minus(10, ChronoUnit.MICROS));
    //        Date dateOfDeath = Date.from(Instant.now().plus(10, ChronoUnit.MICROS));
    //        String span = "";
    //        dateOfDeath = null;
    //        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
    //        if (dateOfDeath == null) {
    //            span = "(born: " + formatter.format(dateofbirth) + ")";
    //        } else {
    //            span = "(" + formatter.format(dateofbirth) + " - " + formatter.format(dateOfDeath) + ")";
    //        }
    //        System.out.println("span is: " + span);
    //
    //        System.out.println("dateOfBirth: " + dateofbirth);
    //
    //
    //        String dateString = "Fri Oct 6";
    //    }

    @Test
    void testkryo() {
        KryoSerializer.validateObjectIsRoundTripSerializable(Author.class);
        System.out.println("ok");
    }

//    @Test
//    void generateAuthorAttribtesSource() {
//        String code = AttributeSourceGenerator.generateAttributesForPastingIntoTargetClass(Author.class);
//        System.out.println("code = " + code);
//    }
//
//
//    @Test
//    void generateBookAttribtesSource() {
//        String code = AttributeSourceGenerator.generateAttributesForPastingIntoTargetClass(Book.class);
//        System.out.println("code = " + code);
//    }

    @Test
    void getAuthors() throws Exception {
        var authors = jsonAuthorsParser.parseJsonAuthors(authorsResource);
        System.out.println("authors = " + authors.size());
        //        jsonAuthors.stream().sorted().map(JsonAuthor::getName).forEach(name -> System.out.printf(
        //          "%s list books with title, author, language, genres, publishers, isbn, publication date MMMM d, yyyy%n",
        //          name));

        //        IndexedCollection<Author> authorsIndexedCollection = new ConcurrentIndexedCollection<Author>();
        ////        authorsIndexedCollection.addIndex(NavigableIndex.onAttribute(Author.AUTHOR_NAME));
        ////        authorsIndexedCollection.addIndex(NavigableIndex.onAttribute(Author.GENRES));
        //        authorsIndexedCollection.addAll(authors);
        //
        ////        authorsIndexedCollection.stream().findFirst().ifPresent(author -> {System.out.println("first author is: " + author);});
        //
        ////        Query<Author> query = QueryFactory.equal(Author.AUTHOR_NAME, "Harper Lee");
        ////        authorsIndexedCollection.retrieve(query).forEach(author -> {
        ////            System.out.println("retrieved author is: " + author);
        ////        });
        //
        ////        query = QueryFactory.in(Author.NATIONALITY, "British", "English");
        ////        try(var resultSet = authorsIndexedCollection.retrieve(query)) {
        ////            resultSet.forEach(author -> {System.out.println("(nationality) author is: " + author.getNationality());});
        ////        }
//        Map<String, ? extends Attribute<Author, ?>> foo =
//          createAttributes(Author.class, member -> {
//              return true;// member.getModifiers() ==1;
//          }, AttributeNameProducers.USE_HUMAN_READABLE_NAMES_FOR_GETTERS);
//
//        Map<String, Attribute<Author, ?>> attributes = new TreeMap<>();
//        attributes.put(AuthorAttributes.ID.getAttributeName(), AuthorAttributes.ID);

        //
        ////          createAttributes(Author.class, member -> {
        ////              System.out.println("MEMBER: " + member.getName() + ", synthetic?: " + member.isSynthetic());
        ////              System.out.println("access flags: " + member.accessFlags());
        ////              System.out.println("access modifiers: " + member.getModifiers());
        ////              boolean foo1 = member.getModifiers() != 1;
        ////              System.out.println("returning " + foo1 + " for " + member);
        ////              return foo1;
        ////          });
        //        SQLParser<Author> parser = SQLParser.forPojoWithAttributes(Author.class, foo);
        //
        //        ResultSet<Author> results = parser.retrieve(authorsIndexedCollection,
        //          """
        //            SELECT * from authors WHERE nationality IN('English','British')
        //            """
        //        );
        //        results.forEach(author -> System.out.println(author.getNationality())); // Prints: Ford Focus, Ford Fusion, Honda Accord

    }

}

