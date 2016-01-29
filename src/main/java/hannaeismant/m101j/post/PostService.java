package hannaeismant.m101j.post;

import java.util.List;

public interface PostService {

    Post create(final String _title, final String _text, final String _tags);

    List<Post> findLast(final int _count);
}
