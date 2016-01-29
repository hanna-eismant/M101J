package hannaeismant.m101j.post;

import org.bson.Document;

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

    private Post buildPostObject(final Document _document) {
        Post post = new Post();
        post.title = _document.getString(PostDAO.TITLE_FIELD);
        post.text = _document.getString(PostDAO.TEXT_FIELD);
        post.tags = _document.getString(PostDAO.TAGS_FIELD);
        post.permalink = _document.getString(PostDAO.PERMALINK_FIELD);

        return post;
    }

    public void setPostDAO(final PostDAO _postDAO) {
        postDAO = _postDAO;
    }
}
