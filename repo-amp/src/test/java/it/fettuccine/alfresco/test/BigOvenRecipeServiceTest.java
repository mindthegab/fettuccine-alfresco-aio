package it.fettuccine.alfresco.test;
import static org.junit.Assert.assertNotNull;
import it.fettuccine.alfresco.recipe.Recipe;
import it.fettuccine.alfresco.recipe.RecipeService;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;

/**
 * A simple class demonstrating how to run out-of-container tests 
 * loading Alfresco application context.
 * 

 *
 */

@RunWith(RemoteTestRunner.class)
@Remote(runnerClass=SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:alfresco/application-context.xml")
public class BigOvenRecipeServiceTest {
    
    static Logger log = Logger.getLogger(BigOvenRecipeServiceTest.class);

    
    @Autowired
    @Qualifier("recipeService")
    protected RecipeService recipeService;
    
    @Test
    public void testWiring() {
        assertNotNull(recipeService);
    }
    
    @Test
    public void testGetRotd() {
    	int i = 0;
    	do{
    		Recipe recipe = recipeService.getRotd();
            assertNotNull(recipe);
            assertNotNull(recipe.getTitle());
            assertNotNull(recipe.getInstructions());
            assertNotNull(recipe.getId());
            assertNotNull(recipe.getUrl());
            i++;
    	}
    	while (i == 10);
    }

}
