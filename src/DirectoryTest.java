import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A JUnit test class for testing the public methods of the Directory class
 * @author Matias Daneels
 * @version 1.0
 */
public class DirectoryTest {

    RootDirectory rootDirectory;
    Directory testDirectory1;
    File testFile1;

    @Before
    public void setUpFixture(){
        rootDirectory = new RootDirectory("root", true);

        testDirectory1 = new Directory("testDirectory1", rootDirectory, true);

        testFile1 = new File("testFile1", 50, true, testDirectory1, "java");
    }

    @Test
    public void testRootDirectoryValidName(){
        assertTrue(RootDirectory.isValidName("validName123_-"));
        assertFalse(RootDirectory.isValidName(".invalidName123."));
    }

    @Test
    public void testDirectoryValidName(){
        assertTrue(Directory.isValidName("validName123_-"));
        assertFalse(Directory.isValidName(".invalidName123."));
    }


    @Test
    public void testDirectoryValidNameConstructor(){
        Directory testDirectory2 = new Directory(".invalidName.", rootDirectory, true);
        assertEquals("new_directory", testDirectory2.getName());
        Directory testDirectory3 = new Directory("validName123_-", rootDirectory, true);
        assertEquals("validName123_-", testDirectory3.getName());
    }


    @Test
    public void testRootDirectoryValidNameConstructor(){
        RootDirectory testRootDirectory2 = new RootDirectory(".invalidName.", true);
        assertEquals("new_root_directory", testRootDirectory2.getName());
        RootDirectory testRootDirectory3 = new RootDirectory("validName123_-", true);
        assertEquals("validName123_-", testRootDirectory3.getName());
    }

    @Test
    public void testDirectorySetWritable(){
        assertTrue(testDirectory1.isWritable());
        testDirectory1.setWritable(false);
        assertFalse(testDirectory1.isWritable());
    }

    @Test
    public void testDirectoryDefaultItems(){
        Directory emptyTestDirectory = new Directory("emptyTestDirectory", rootDirectory, true);
        assertEquals(new ArrayList<>(), emptyTestDirectory.getItems());
    }

    @Test
    public void testDirectoryMoveItem(){
        Directory testDirectory2 = new Directory("testDirectory2", rootDirectory, true);
        testFile1.move(testDirectory2);
        assertTrue(testDirectory2.getItems().contains(testFile1));
    }
}
