
package graeme.org.neo4jfunk;

import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphDatabaseService db = new EmbeddedGraphDatabase("/tmp/neo");
		Transaction tx = db.beginTx();
		try
		{
			Node theDoctor = db.createNode();
			theDoctor.setProperty("character", "The Doctor");

			Node susan = db.createNode(); susan.setProperty("firstname", "Susan"); susan.setProperty("lastname", "Campbell");
			susan.createRelationshipTo(theDoctor, DynamicRelationshipType.withName("COMPANION_OF"));
			
			tx.success();
		}
		finally
		{
			tx.finish();
		}
	}

}
