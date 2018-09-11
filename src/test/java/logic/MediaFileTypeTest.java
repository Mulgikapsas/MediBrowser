package logic;

import com.qa.dao.MediaFileType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.qa.dao.MediaFileType.AAC;
import static com.qa.dao.MediaFileType.AVI;
import static com.qa.dao.MediaFileType.MP3;
import static com.qa.dao.MediaFileType.MP4;
import static com.qa.dao.MediaFileType.WAV;

class MediaFileTypeTest {

    @Test
    void getByPath() {
        Assertions.assertEquals(MP3, MediaFileType.getByPath("C:/music.mp3"));
        Assertions.assertEquals(AAC, MediaFileType.getByPath("C:/file/storage.aac"));
        Assertions.assertEquals(WAV, MediaFileType.getByPath("C:/file/storage.wav"));
        Assertions.assertEquals(MP4, MediaFileType.getByPath("C:/file/storage.mp4"));
        Assertions.assertEquals(AVI, MediaFileType.getByPath("C:/file/storage.avi"));
    }
}
