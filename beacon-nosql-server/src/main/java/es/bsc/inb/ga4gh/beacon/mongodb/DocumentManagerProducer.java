/**
 * *****************************************************************************
 * Copyright (C) 2022 ELIXIR ES, Spanish National Bioinformatics Institute (INB)
 * and Barcelona Supercomputing Center (BSC)
 *
 * Modifications to the initial code base are copyright of their respective
 * authors, or their employers as appropriate.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * *****************************************************************************
 */

package es.bsc.inb.ga4gh.beacon.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.servlet.ServletContext;
import org.eclipse.jnosql.communication.mongodb.document.MongoDBDocumentConfiguration;

/**
 * @author Dmitry Repchevsky
 */

@ApplicationScoped
public class DocumentManagerProducer {

    public final static String MONGODB_URI_PROPERTY = "mongodb.url";
    
    @Inject 
    private ServletContext ctx;

    private DocumentCollectionManager manager;
    
    @PostConstruct
    public void init() {
        ConnectionString con = new ConnectionString(ctx.getInitParameter(MONGODB_URI_PROPERTY));
        DocumentCollectionManagerFactory factory = new MongoDBDocumentConfiguration().get(MongoClients.create(con));
        manager = factory.get(con.getDatabase());
    }
    
    @Produces
//    @Database(value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getManagerFactory() {
        return manager;
    }

    @PreDestroy
    public void destroy() {
        manager.close();
    }
}
