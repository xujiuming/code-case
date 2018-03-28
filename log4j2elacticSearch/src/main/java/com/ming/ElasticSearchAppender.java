package com.ming;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;


/**
 * log4j2  和es交互的适配器
 *
 * @author ming
 * @date 2018-03-28 11:39
 */
@Plugin(name = "esAppender",category = "core",elementType = "appender",printObject = true)
public class ElasticSearchAppender extends AbstractAppender {




        /*  接收配置文件中的参数 */
        @PluginFactory
        public static FileAppender createAppender(@PluginAttribute("name") String name,
                                                  @PluginAttribute("fileName") String fileName,
                                                  @PluginElement("Filter") final Filter filter,
                                                  @PluginElement("Layout") Layout<? extends Serializable> layout,
                                                  @PluginAttribute("ignoreExceptions") boolean ignoreExceptions) {
            if (name == null) {
                LOGGER.error("no name defined in conf.");
                return null;
            }
            if (layout == null) {
                layout = PatternLayout.createDefaultLayout();
            }
            // 创建文件
            if (!createFile(fileName)) {
                return null;
            }
            return new FileAppender(name, filter, layout, ignoreExceptions, fileName);
        }

        private static boolean createFile(String fileName) {
            Path filePath = Paths.get(fileName);
            try {
                // 每次都重新写文件，不追加
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                }
                Files.createFile(filePath);
            } catch (IOException e) {
                LOGGER.error("create file exception", e);
                return false;
            }
            return true;
        }

        private void writerFile(byte[] log) {
            try {
                Files.write(Paths.get(fileName), log, StandardOpenOption.APPEND);
            } catch (IOException e) {
                LOGGER.error("write file exception", e);
            }
        }


}
