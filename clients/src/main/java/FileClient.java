import com.forsvarir.common.utils.file.FileWalker;

import java.nio.file.Path;

public class FileClient {
    public static void main(String[] args) {
        FileWalker.walk(Path.of("/tmp"))
                .forEach(f->System.out.println(f.toString()));
    }
}
