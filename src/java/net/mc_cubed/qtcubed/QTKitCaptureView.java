//
//  QTKitCaptureView.java
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

package net.mc_cubed.qtcubed;

import com.apple.eawt.CocoaComponent;
import java.awt.Dimension;

/**
 *
 * @author shadow
 */
public class QTKitCaptureView extends CocoaComponent {
    public final int SET_CAPTURE_SESSION = 1;

    protected QTKitCaptureSession captureSession;
    
    @Override
    native public long createNSViewLong();


    @Override
    public int createNSView() {
        return (int)createNSViewLong();
    }

    @Override
    public Dimension getMaximumSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Dimension getMinimumSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Dimension getPreferredSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCaptureSession(QTKitCaptureSession session) {
        this.captureSession = session;
        sendMessage(SET_CAPTURE_SESSION, session.getCaptureSessionRef());
    }
}
