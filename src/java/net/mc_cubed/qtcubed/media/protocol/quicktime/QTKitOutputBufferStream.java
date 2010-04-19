//
//  QTCubedOutputBufferStream.java
//  QTCubed
//
//  Created by Chappell Charles on 10/02/19.
//  Copyright (c) 2010 MC Cubed, Inc. All rights reserved.
//
//  This program is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; either version 2 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License along
//  with this program; if not, write to the Free Software Foundation, Inc.,
//  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
//
//
//  This is also dual licensed as proprietary software, and may be used in
//  commercial/proprietary software products by obtaining a license from:
//  MC Cubed, Inc
//  1-3-4 Kamikizaki, Urawa-ku
//  Saitama, Saitama, 330-0071
//  Japan
//
//  Email: info@mc-cubed.net
//  Website: http://www.mc-cubed.net/

package net.mc_cubed.qtcubed.media.protocol.quicktime;

import java.awt.Dimension;
import java.io.IOException;
import javax.media.Buffer;
import javax.media.Format;
import javax.media.protocol.BufferTransferHandler;
import javax.media.protocol.PushBufferStream;
import net.mc_cubed.qtcubed.QTKitCaptureDataDelegate;
import net.mc_cubed.qtcubed.QTKitFormatUtils;
import net.mc_cubed.qtcubed.QTKitSampleBuffer;

/**
 *
 * @author shadow
 */
abstract public class QTKitOutputBufferStream implements PushBufferStream,QTKitCaptureDataDelegate {

    QTKitSampleBuffer sampleData;

    BufferTransferHandler bth;
    
    public void read(Buffer buffer) throws IOException {
        if (sampleData != null) {
            buffer.setData(sampleData.getRawData());
            if (sampleData.getDataClass() == Format.byteArray) {
                buffer.setLength(((byte[])sampleData.getRawData()).length);
            } else if (sampleData.getDataClass() == Format.shortArray) {
                buffer.setLength(((short[])sampleData.getRawData()).length);
            } else if (sampleData.getDataClass() == Format.intArray) {
                buffer.setLength(((int[])sampleData.getRawData()).length);
            }

            Format format = QTKitFormatUtils.PixelFormatToJMF(sampleData.getPixelFormat(), new Dimension(sampleData.getWidth(),sampleData.getHeight()), sampleData.getFrameRate());
            buffer.setFormat(format);
            sampleData = null;
        } else {
            buffer.setLength(0);
        }
    }

    public void setTransferHandler(BufferTransferHandler bth) {
        this.bth = bth;
    }

    public long getContentLength() {
            if (sampleData.getDataClass() == Format.byteArray) {
                return ((byte[])sampleData.getRawData()).length;
            } else if (sampleData.getDataClass() == Format.shortArray) {
                return ((short[])sampleData.getRawData()).length;
            } else if (sampleData.getDataClass() == Format.intArray) {
                return ((int[])sampleData.getRawData()).length;
            } else {
                return 0;
            }
    }

    public void nextSample(QTKitSampleBuffer buffer) {
        this.sampleData = buffer;

        if (bth != null) {
            bth.transferData(this);
        }
    }

    public QTKitOutputBufferStream(QTCubedDelegator delegator) {
        delegator.setDataDelegate(this);
    }

}
