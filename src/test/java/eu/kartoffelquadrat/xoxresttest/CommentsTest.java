package eu.kartoffelquadrat.xoxresttest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

/**
 * All Unit tests for Comments Resources
 */
public class CommentsTest extends RestTestUtils {

    /**
     * Test retrieval of comments for default book
     */
    @Test
    public void testIsbnsIsbnCommentsGet() throws UnirestException {

        // Try to retrieve comments for default book
        HttpResponse<String> comments = Unirest.get(getServiceURL("/isbns/9780739360385/comments")).asString();
        verifyOk(comments);

        // Verify and return comments content
        String body = comments.getBody();
        assert body.contains("A must read!");
        assert body.contains("Amazing book!");
    }

    /**
     * Test adding a comment (is tested on a new book with random ISBN, to avoid state collisions on test re-run)
     */
    @Test
    public void testIsbnsIsbnCommentsPost() throws UnirestException {

        // Add fake book to operate on (so this test can be repeated) - extra method because unit tests do not allow return values.
        addCommentToRandomBook();
    }

    /**
     * Test removing all comments of a book.
     */
    @Test
    public void testIsbnsIsbnCommentsDelete() throws UnirestException {

        String isbn = addCommentToRandomBook();

        // delete all comments and verify no more comments are stored
        HttpResponse<String> deleteResponse = Unirest.delete(getServiceURL("/isbns/" + isbn + "/comments")).asString();
        verifyOk(deleteResponse);

        // Verify no more comments stored for book
        HttpResponse<String> comments = Unirest.get(getServiceURL("/isbns/" + isbn + "/comments")).asString();
        verifyOk(comments);
        assert (comments.getBody().equals("{}"));
    }

    /**
     * Test modifying an existing comment. First creates a new comment for a random book to avoid collisions on test
     * re-execution.
     */
    @Test
    public void testIsbnsIsbnCommentsCommentPost() throws UnirestException {

        // Add a new comment to a new random book
        String randomIsbn = addCommentToRandomBook();

        // Try to modify the comment
        // Find comment ID
        HttpResponse<String> comments = Unirest.get(getServiceURL("/isbns/" + randomIsbn + "/comments")).asString();
        String commentId = comments.getBody().split(":")[0].substring(1).replace("\"", "");

        // Modify comment
        String newComment = "NEWCOMMENT";
        HttpResponse<String> addCommentReply = Unirest.post(getServiceURL("/isbns/" + randomIsbn + "/comments/" + commentId)).header("Content-Type", "application/json").body(newComment).asString();
        verifyOk(addCommentReply);

        // Verify change
        // Try to retrieve comments for default book, verify comment
        HttpResponse<String> updatedCommentReply = Unirest.get(getServiceURL("/isbns/" + randomIsbn + "/comments")).asString();
        verifyOk(updatedCommentReply);
        assert updatedCommentReply.getBody().contains(newComment);
    }

    /**
     * Test removing a specific comment by ID. Creates a new random book first to avoid collisions on test re-execution.
     */
    @Test
    public void testIsbnsIsbnCommentsCommentDelete() throws UnirestException {

        // Add a new comment to a new random book
        String randomIsbn = addCommentToRandomBook();

        // Try to delete the comment
        // Find comment ID
        HttpResponse<String> comments = Unirest.get(getServiceURL("/isbns/" + randomIsbn + "/comments")).asString();
        String commentId = comments.getBody().split(":")[0].substring(1).replace("\"", "");

        // Actually try to delete it
        HttpResponse<String> deleteCommentReply = Unirest.delete(getServiceURL("/isbns/" + randomIsbn + "/comments/"+commentId)).asString();
        verifyOk(deleteCommentReply);

        // Verify no more comments stored for book
        HttpResponse<String> commentsAfterDeletion = Unirest.get(getServiceURL("/isbns/" + randomIsbn + "/comments")).asString();
        verifyOk(commentsAfterDeletion);
        assert (commentsAfterDeletion.getBody().equals("{}"));
    }

    /**
     * Helper method to add a comment to a random new book. Returns ISBN of the book that has been generated.
     *
     * @return ISBN of the test book as String.
     */
    private String addCommentToRandomBook() throws UnirestException {
        String randomIsbn = getRandomIsbn();
        HttpResponse addBookAck = addTestBook(randomIsbn);
        verifyOk(addBookAck);

        // Try to add comment
        String commentBody = "So much better than the movie";
        HttpResponse<String> addComment = Unirest.post(getServiceURL("/isbns/" + randomIsbn + "/comments")).header("Content-Type", "application/json").body(commentBody).asString();
        verifyOk(addComment);

        // Verify comments for new book.
        HttpResponse<String> comments = Unirest.get(getServiceURL("/isbns/" + randomIsbn + "/comments")).asString();
        assert comments.getBody().contains(commentBody);
        return randomIsbn;
    }
}
