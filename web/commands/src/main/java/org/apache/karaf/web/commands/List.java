/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.web.commands;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.karaf.web.WebBundle;
import org.apache.karaf.web.WebContainerService;

@Command(scope = "web", name = "list", description = "Lists details for war bundles.")
public class List extends OsgiCommandSupport {
    
    private WebContainerService webContainerService;
    
    public void setWebContainerService(WebContainerService webContainerService) {
        this.webContainerService = webContainerService;
    }
    
    public Object doExecute() throws Exception {
        java.util.List<WebBundle> webBundles = webContainerService.list();
        if (webBundles != null && !webBundles.isEmpty()) {
            String headers = String.format("%d4 %s6 %s6 %s6 %s4 %s30 %s30", "ID", "State", "Web-State", "Level", "Web-ContextPath", "Name");
            System.out.println(headers);
            for (WebBundle webBundle : webBundles) {
                String display = String.format("%d4 %s6 %s6 %s6 %d4 %s30 %s30",
                        webBundle.getBundleId(),
                        webBundle.getState(),
                        webBundle.getWebState(),
                        webBundle.getLevel(),
                        webBundle.getContextPath(),
                        webBundle.getName());
                System.out.println(display);
            }
            
        }        
        return null;
    }
    
}
