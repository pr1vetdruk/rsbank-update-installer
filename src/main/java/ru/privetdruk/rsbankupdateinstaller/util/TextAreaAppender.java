package ru.privetdruk.rsbankupdateinstaller.util;

import javafx.scene.control.TextArea;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;


public class TextAreaAppender extends WriterAppender {
    public static TextArea textArea;

    @Override
    public void append(LoggingEvent event) {
        if (textArea != null)
            textArea.appendText(this.layout.format(event));
    }
}
