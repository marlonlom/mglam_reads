/*
 * Copyright 2015 marlonlom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.malm.mglam_reads.backend.sax;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import co.malm.mglam_reads.backend.model.FeedChannel;

/**
 * Sax implementation class for parsing XML feed contents from remote feed url
 *
 * @author marlonlom
 */
public class RssFeedXmlParser {

    /**
     * Logging utility
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(RssFeedXmlParser.class.getName());

    /**
     * SAX default Parser factory instance
     *
     * @see javax.xml.parsers.SAXParserFactory
     */
    private SAXParserFactory factory = SAXParserFactory.newInstance();

    /**
     * SAX Handler for parsing Rss Feed contents
     *
     * @see RssFeedHandler
     */
    private RssFeedHandler handler = new RssFeedHandler();

    /**
     * SAX Parser for reading Rss Feed contents
     *
     * @see javax.xml.parsers.SAXParser
     */
    private SAXParser saxParser;

    /**
     * Default Constructor
     */
    public RssFeedXmlParser() {
    }

    /**
     * Parses xml content into an {@link co.malm.mglam_reads.backend.model.FeedChannel} instance
     *
     * @param xmlContents text to read/parse
     * @see co.malm.mglam_reads.backend.model.FeedChannel
     */
    public void performParsing(String xmlContents) {
        try {
            saxParser = factory.newSAXParser();

            InputStream inputStream = new ByteArrayInputStream(xmlContents.trim().getBytes(Charset.forName("UTF-8")));

            saxParser.parse(new InputSource(inputStream), handler);

        } catch (ParserConfigurationException e) {
            LOGGER.severe(e.getMessage());
        } catch (SAXException e) {
            LOGGER.severe(e.getMessage());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    /**
     * Returns RSS Feed Channel instance with information added
     *
     * @return Feed channel instance
     */
    public FeedChannel getFeedChannel() {
        return this.handler.getRssChannel();
    }
}
