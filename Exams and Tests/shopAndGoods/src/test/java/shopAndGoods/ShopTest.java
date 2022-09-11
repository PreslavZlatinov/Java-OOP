package shopAndGoods;


import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShopTest {

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetShelves(){
        Shop shop = new Shop();
        Map<String,Goods> goodsMap = shop.getShelves();
        goodsMap.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddGoods() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("Chorap","123");

        shop.addGoods("Shelf1",goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddGoods1() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("Chorap","123");
        Goods goods1 = new Goods("Chorap1","123");

        shop.addGoods("Shelves1",goods);
        shop.addGoods("Shelves1",goods1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_AddGoods2() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("Chorap","123");

        shop.addGoods("Shelves1",goods);
        shop.addGoods("Shelves2",goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveGoods() throws OperationNotSupportedException {
        Shop shop = new Shop();

        shop.removeGoods("Shelves69",null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveGoods1() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("Chorap","123");

        shop.removeGoods("Shelves1",goods);
    }

    @Test
    public void test_RemoveGoods2() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("Chorap","123");

        shop.addGoods("Shelves1",goods);

        shop.removeGoods("Shelves1",goods);

        assertNull(shop.getShelves().get("Shelves1"));
    }

    @Test
    public void test_Print() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("Chorap","123");

        shop.addGoods("Shelves1",goods);

        String shelves1 = shop.removeGoods("Shelves1", goods);

        String expected = String.format("Goods: %s is removed successfully!",goods.getGoodsCode());

        assertEquals(expected,shelves1);
    }
}