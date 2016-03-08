/**
* TestHockeyTeam.java
*
* @author Trevor Douglas
*   <A HREF="mailto:douglatr@uregina.ca"> (douglatr@uregina.ca) </A>
*
* Original code copyright © Mar 15, 2010 Trevor Douglas.  Modifications can be made
* with Author's consent.
* @version Mar 15, 2010
*
**/

package edu.ense475.Buggy;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * @author tdouglas
 *
 */
public class TestHockeyTeam extends TestCase {

	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyTeam#HockeyTeam(java.lang.String)}.
	 */
	public void testHockeyTeam() {
		HockeyTeam ht = new HockeyTeam("Toronto Maple Leafs");
		assertEquals("Toronto Maple Leafs" , ht.teamName);
	}

	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyTeam#addPlayer(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	public void testAddPlayer() {

		HockeyTeam ht = new HockeyTeam("Chicago BlackHawks");
		HockeyPlayer p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertTrue(ht.addPlayer(p));

		p = new HockeyPlayer("R", "Kris","Versteeg",79);
		assertTrue(ht.addPlayer(p));
		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.addPlayer(p));
		p = new HockeyPlayer("C", "Jonathan","Toews",80);
		assertTrue(ht.addPlayer(p));
	}

	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyTeam#addPlayer(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	public void testAddPlayerRosterCheck() {

		ArrayList<HockeyPlayer> checkRoster = new ArrayList<HockeyPlayer>();
		ArrayList<HockeyPlayer> actualRoster;

		HockeyTeam ht = new HockeyTeam("Chicago BlackHawks");
		HockeyPlayer p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertTrue(ht.addPlayer(p));
		checkRoster.add(p);
		p = new HockeyPlayer("R", "Kris","Versteeg",79);
		assertTrue(ht.addPlayer(p));
		checkRoster.add(p);
		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.addPlayer(p));
		checkRoster.add(p);
		p = new HockeyPlayer("C", "Jonathan","Toews",80);
		assertTrue(ht.addPlayer(p));
		checkRoster.add(p);

		actualRoster = ht.getRoster();
		for(int i=0; i<actualRoster.size(); ++i)
		{
			assertEquals(checkRoster.get(i).getFirstName(),
					     actualRoster.get(i).getFirstName());
		}

	}



	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyTeam#addPlayer(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	public void testAddPlayerRosterDuplicate() {

		HockeyTeam ht = new HockeyTeam("Chicago BlackHawks");
		HockeyPlayer p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertTrue(ht.addPlayer(p));
		p = new HockeyPlayer("R", "Kris","Versteeg",79);
		assertTrue(ht.addPlayer(p));

		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.addPlayer(p));

        //Duplicate player
		p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertFalse(ht.addPlayer(p));

		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertFalse(ht.addPlayer(p));
		
	}


	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyTeam#deletePlayer(java.lang.String, java.lang.String)}.
	 */
	public void testDeletePlayer() {
		HockeyTeam ht = new HockeyTeam("Chicago BlackHawks");
		HockeyPlayer p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertTrue(ht.addPlayer(p));

		p = new HockeyPlayer("R", "Kris","Versteeg",79);
		assertTrue(ht.addPlayer(p));
		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.addPlayer(p));
		p = new HockeyPlayer("C", "Jonathan","Toews",80);
		assertTrue(ht.addPlayer(p));
		assertTrue(ht.deletePlayer(p));
	}

	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyTeam#addPlayer(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	public void testDeletePlayerRosterCheck() {

		ArrayList<HockeyPlayer> checkRoster = new ArrayList<HockeyPlayer>();
		ArrayList<HockeyPlayer> actualRoster;

		HockeyTeam ht = new HockeyTeam("Chicago BlackHawks");
		HockeyPlayer p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertTrue(ht.addPlayer(p));
		checkRoster.add(p);
		p = new HockeyPlayer("R", "Kris","Versteeg",79);
		assertTrue(ht.addPlayer(p));
		checkRoster.add(p);
		
		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.addPlayer(p));
		//Do not add to our check Roster

		p = new HockeyPlayer("C", "Jonathan","Toews",80);
		assertTrue(ht.addPlayer(p));
		checkRoster.add(p);

		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.deletePlayer(p));

		
		actualRoster = ht.getRoster();
		for(int i=0; i<actualRoster.size(); ++i)
		{
			assertEquals(checkRoster.get(i).getFirstName(),
					     actualRoster.get(i).getFirstName());
		}

	}

	public void testGetPlayer() {

		HockeyPlayer retreivePlayer;
		
		HockeyTeam ht = new HockeyTeam("Chicago BlackHawks");
		HockeyPlayer p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertTrue(ht.addPlayer(p));

		HockeyPlayer testPlayer = new HockeyPlayer("R", "Kris","Versteeg",79);
		assertTrue(ht.addPlayer(testPlayer));
		
		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.addPlayer(p));
		
		p = new HockeyPlayer("C", "Jonathan","Toews",80);
		assertTrue(ht.addPlayer(p));
		
		retreivePlayer = ht.getPlayer(testPlayer);
		assertNotNull(retreivePlayer);
		assertEquals(testPlayer.getFirstName(), retreivePlayer.getFirstName());
		
		testPlayer = new HockeyPlayer("R", "Kis","Versteeg",79);
		
		retreivePlayer = ht.getPlayer(testPlayer);
		assertNull(retreivePlayer);
		
	}
	/**
	 * Test method for {@link edu.ense475.Buggy.HockeyTeam#editPlayer(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	public void testEditPlayer() {

		HockeyTeam ht = new HockeyTeam("Chicago BlackHawks");

		HockeyPlayer p = new HockeyPlayer("R", "Martin","Havlat",82);
		assertTrue(ht.addPlayer(p));

		p = new HockeyPlayer("R", "Kris","Versteeg",79);
		assertTrue(ht.addPlayer(p));

		
		p = new HockeyPlayer("R", "Patrick","Kane",80);
		assertTrue(ht.addPlayer(p));

		
		p = new HockeyPlayer("C", "Jonathan","Toews",80);
		assertTrue(ht.addPlayer(p));

		HockeyPlayer editPlayer = new HockeyPlayer("R", "Mrtin","Havlat",75);
		assertFalse(ht.editPlayer(editPlayer));
		editPlayer = new HockeyPlayer("R", "Martin","Havlat",75);
		assertTrue(ht.editPlayer(editPlayer));
		
		HockeyPlayer getPlayer = ht.getPlayer(editPlayer);
		assertEquals(75,getPlayer.getRating());

	}

}
