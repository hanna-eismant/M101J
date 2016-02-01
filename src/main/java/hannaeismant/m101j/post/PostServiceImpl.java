package hannaeismant.m101j.post;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {

    private PostDAO postDAO;

    public PostServiceImpl(final PostDAO _postDAO) {
        postDAO = _postDAO;
    }

    @Override
    public Post create(final String _title, final String _text, final String _tags) {
        Document document = postDAO.create(_title, _text, _tags);
        return buildPostObject(document);
    }

    @Override
    public List<Post> findLast(final int _count) {
        List<Post> posts = new ArrayList<>(_count);
        List<Document> documents = postDAO.findLast(_count);

        for (Document document : documents) {
            Post post = buildPostObject(document);
            posts.add(post);
        }

        return posts;
    }

    @Override
    public Post find(final String _permalink) {
        Document document = postDAO.find(_permalink);
        return buildPostObject(document);
    }

    private Post buildPostObject(final Document _document) {
        Post post = new Post();
        post.title = _document.getString(PostDAO.TITLE_FIELD);
        post.text = _document.getString(PostDAO.TEXT_FIELD);
        post.tags = _document.getString(PostDAO.TAGS_FIELD);
        post.permalink = _document.getString(PostDAO.PERMALINK_FIELD);

        return post;
    }
}
