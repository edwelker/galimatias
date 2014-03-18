/*
 * Copyright (c) 2014 Santiago M. Mola <santi@mola.io>
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a
 *   copy of this software and associated documentation files (the "Software"),
 *   to deal in the Software without restriction, including without limitation
 *   the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *   and/or sell copies of the Software, and to permit persons to whom the
 *   Software is furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 *   OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *   FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *   DEALINGS IN THE SOFTWARE.
 */

package io.mola.galimatias.canonicalize;

import io.mola.galimatias.GalimatiasParseException;
import io.mola.galimatias.URL;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(JUnit4.class)
public class RFC3986CanonicalizerTest {

    @Test
    public void test() throws GalimatiasParseException {
        final URLCanonicalizer canon = new RFC3986Canonicalizer();
        for (final String[] pair : new String[][] {
                new String[]{ "http://example.com/^{}|[]`~", "http://example.com/%5E%7B%7D%7C%5B%5D%60~" },
                new String[]{ "http://example.com/?^{}|[]`~", "http://example.com/?%5E%7B%7D%7C%5B%5D%60~" },
                new String[]{ "http://example.com/#^{}|[]`~", "http://example.com/#%5E%7B%7D%7C%5B%5D%60~" }
        }) {
            assertThat(canon.canonicalize(URL.parse(pair[0])).toString())
                .isEqualTo(URL.parse(pair[1]).toString());
        }
    }

}
