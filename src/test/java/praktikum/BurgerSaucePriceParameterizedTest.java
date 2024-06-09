package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerSaucePriceParameterizedTest {
    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;
    Burger burger = new Burger();

    public BurgerSaucePriceParameterizedTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getPriceData() {
        return new Object[][]{
                {0, 0, 0},
                {0, 100, 100},
                {100, 0, 200},
                {100, 500, 700},
        };
    }

    @Mock
    Bun bun;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "something", ingredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        float burgerPrice = burger.getPrice();

        assertEquals(expectedPrice, burgerPrice, 0);
    }
}
