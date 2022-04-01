import com.nantian.galaxy.gemini.util.ResourceBundleUtils;
import com.nantian.test.java.util.util.FileToFileUtil;
import com.nantian.test.java.util.util.PropertiesUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/2/20
 */
public class Main {
    public static void main(String[] args) throws IOException {
            Properties property = null;
            String sourcePath=null;
            String sinkPath=null;
            try {
                property = PropertiesUtils.getProperty("application.properties");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (property.get("source.path") != null){
                sourcePath = ResourceBundleUtils.getString("application", "source.path");
            }
            if (property.get("sink.path") != null){
                sinkPath = ResourceBundleUtils.getString("application", "sink.path");
            }
                FileToFileUtil.nioCpoy(sourcePath, sinkPath, 1024);
        }
}
