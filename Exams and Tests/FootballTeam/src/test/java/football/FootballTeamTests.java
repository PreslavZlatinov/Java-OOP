package football;

import org.junit.Assert;
import org.junit.Test;

public class FootballTeamTests {

    @Test(expected = NullPointerException.class)
    public void test_ConstructorCreate_WithNameNull(){
        FootballTeam footballTeam = new FootballTeam(null,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ConstructorCreate_WithCapacityNegative(){
        FootballTeam footballTeam = new FootballTeam("Manchester United",-1);
    }

    @Test
    public void test_ConstructorCreate_ShouldCreate(){
        FootballTeam footballTeam = new FootballTeam("Manchester United",69);

        Assert.assertEquals("Manchester United", footballTeam.getName());
        Assert.assertEquals(69, footballTeam.getVacantPositions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddPlayer_ShouldThrow() {
        FootballTeam footballTeam = new FootballTeam("Manchester United",1);

        Footballer footballer = new Footballer("HarryMaguire");
        Footballer footballer1 = new Footballer("Jacob");

        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
    }

    @Test
    public void test_Remove_ShouldRemove() {
        FootballTeam footballTeam = new FootballTeam("Manchester United",3);


        Footballer footballer = new Footballer("HarryMaguire");
        Footballer footballer1 = new Footballer("Jacob");
        Footballer footballer2 = new Footballer("Preslav");

        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);

        Assert.assertEquals(3,footballTeam.getCount());
        footballTeam.removeFootballer("Preslav");
        Assert.assertEquals(2,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemovePlayer_ShouldThrow() {
        FootballTeam footballTeam = new FootballTeam("Manchester United",3);


        Footballer footballer = new Footballer("HarryMaguire");
        Footballer footballer1 = new Footballer("Jacob");
        Footballer footballer2 = new Footballer("Preslav");

        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);

        footballTeam.removeFootballer("Kalin");
    }

    @Test
    public void test_FootballerForSale() {
        FootballTeam footballTeam = new FootballTeam("Manchester United",3);


        Footballer footballer = new Footballer("HarryMaguire");
        Footballer footballer1 = new Footballer("Jacob");
        Footballer footballer2 = new Footballer("Preslav");

        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);

        boolean isSale = footballer2.isActive();
        Assert.assertTrue(isSale);

        boolean checkActivity = footballTeam.footballerForSale("Preslav").isActive();
        Assert.assertFalse(checkActivity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FootballerForSale_ShouldThrow() {
        FootballTeam footballTeam = new FootballTeam("Manchester United",3);


        Footballer footballer = new Footballer("HarryMaguire");
        Footballer footballer1 = new Footballer("Jacob");
        Footballer footballer2 = new Footballer("Preslav");

        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);


        footballTeam.footballerForSale("Genadi");
    }
}
