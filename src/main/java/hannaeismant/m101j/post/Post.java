package hannaeismant.m101j.post;

public class Post {

    public String title;
    public String text;
    public String tags;
    public String permalink;

    public Post() {
    }

    public Post(final String _title, final String _text, final String _tags, final String _permalink) {
        title = _title;
        text = _text;
        tags = _tags;
        permalink = _permalink;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTags() {
        return tags;
    }

    public String getPermalink() {
        return permalink;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", tags='" + tags + '\'' +
                ", permalink='" + permalink + '\'' +
                '}';
    }
}
