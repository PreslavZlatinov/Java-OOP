package gifts;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class GiftFactoryTests {

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetPresents(){
        GiftFactory giftFactory = new GiftFactory();
        Collection<Gift> presents = giftFactory.getPresents();
        presents.remove(1);
    }

    @Test
    public void test_GetCount(){
        GiftFactory giftFactory = new GiftFactory();
        assertEquals(0,giftFactory.getCount());

        Gift gift = new Gift("boots",12.12);

        giftFactory.createGift(gift);

        assertEquals(1, giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createGift_ShouldReturn_Exception(){
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("boots",12.12);
        Gift sameGift = new Gift("boots",12.13);

        giftFactory.createGift(gift);
        giftFactory.createGift(sameGift);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveGift_ShouldThrow_Exception(){
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.removeGift(null);
    }

    @Test
    public void test_RemoveGift_ShouldReturn_True(){
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = new Gift("boots",12.12);
        giftFactory.createGift(gift);
        boolean isRemoved = giftFactory.removeGift("boots");
        assertTrue(isRemoved);
    }

    @Test
    public void test_GetPresentWithLeastMagic_ShouldReturn_Null(){
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = giftFactory.getPresentWithLeastMagic();
        assertNull(gift);
    }

    @Test
    public void test_GetPresentWithLeastMagic_ShouldReturn_Gift(){
        GiftFactory giftFactory = new GiftFactory();

        Gift giftOne = new Gift("kola",69.69);
        Gift giftTwo = new Gift("tir",169.69);

        giftFactory.createGift(giftOne);
        giftFactory.createGift(giftTwo);

        Gift gift = giftFactory.getPresentWithLeastMagic();

        assertEquals(giftOne.getMagic(),gift.getMagic(),0.00);
    }

    @Test
    public void test_GetPresent_ShouldReturn_Null(){
        GiftFactory giftFactory = new GiftFactory();
        Gift gift = giftFactory.getPresent("kola");
        assertNull(gift);
    }

    @Test
    public void test_GetPresent_ShouldReturn_Gift(){

        GiftFactory giftFactory = new GiftFactory();

        Gift giftOne = new Gift("kola",69.69);
        Gift giftTwo = new Gift("tir",169.69);

        giftFactory.createGift(giftOne);
        giftFactory.createGift(giftTwo);

        Gift gift = giftFactory.getPresent("kola");

        assertEquals(gift.getMagic(),giftOne.getMagic(),0.00);
    }
}
