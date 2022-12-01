import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class novelBuilder {
    public String name;
    public String textXpath;
    private String paginationXpath;
    public driver handler;
    private java.net.URL url;
    private String URL;
    private URI uri;
    public ArrayList<String> chapterUrls;
    public int chapterTotal;

    public novelBuilder(String url){
        //works within chapter URL
        this.textXpath = "/html/body/div[3]/div/div/div/div/div[3]/div[2]/div[2]";
        //works within novel homepage URL; hopefully this is universal for every novel
        this.paginationXpath = "/html/body/div[3]/div/div/div/div[1]/div/div[2]/div/div[2]/div[5]/div[2]/div/div/div[2]/div/ul/li[7]/a";
        this.handler = new driver();
        this.URL = url;
        try {
            this.uri = new URI(this.URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        this.chapterUrls = new ArrayList<String>();

    }

    public void getName(){
        //scrape name of novel from given url
        String path = this.uri.getPath();
        this.name = path.substring(path.lastIndexOf('/') + 1);
    }
    public String getNameGeneral(String path){
        //scrape name of novel from given url
        String name = path.substring(path.lastIndexOf('/') + 1);
        return name;
    }

    public void grabChapterTotal(){
        //should be pointing at this.URL
        String chapterNumber = this.handler.driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[1]/div/div[2]/div/div[2]/div[5]/div[1]/div[2]/span")).getText();
        //the extra stuff is to remove the space and word chapters
        this.chapterTotal = Integer.parseInt(chapterNumber.substring(0,chapterNumber.length()-9));
    }

    public void collectChapterLinks(){
        //scrape links to all chapter from homepage url given
        String currUrl;
        handler.driver.get(this.URL);
        grabChapterTotal();
        List<WebElement> allLinks = driver.driver.findElements(By.tagName("a"));
        //TODO: fix the bug that causes skipping AFTER getting the novel download, load, and cli to work
        //TODO: potentially alter xpath to be collecting html for the chapter text
        while (this.chapterTotal > chapterUrls.size()){
//            System.out.println("while loop works");
            for(WebElement link: allLinks) {
//                System.out.println("for loop works");

                currUrl = link.getAttribute("href");
//                System.out.println(currUrl);
                try{
                    String currName = currUrl.substring(0, urlHelper(2, "/", currUrl));
//                    System.out.println(currName);
                    String chapterOrNot = getNameGeneral(currName);
//                    System.out.println(chapterOrNot);
                    if (chapterOrNot.equals("chapter") && this.chapterUrls.size() != this.chapterTotal) {
                        this.chapterUrls.add(link.getAttribute("href"));
                    } else {
                        continue;
                    }
                } catch (Exception exception){
                    //the path was too short and was out of range so go to next link
                }
            }
            WebDriverWait wait = new WebDriverWait(handler.driver, Duration.ofSeconds(5));

            //sometimes this fails for a novel, i guess the homepages can be dynamic, if limited i could account for types, but thats just patching a sinking ship
            WebElement nextPageEle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(paginationXpath)));
            if (nextPageEle.isDisplayed() && nextPageEle.isEnabled()) {
                nextPageEle.click();
                allLinks = handler.driver.findElements(By.tagName("a"));
            } else {
                break;

            }
        }

    }

    /**
     * credit https://stackoverflow.com/a/54142728/15699199
     * @param nth
     * @param delim
     * @param url
     * @return
     */
    public static int urlHelper(int nth, String delim, String url){
        //given nth from last / get a substring length to define as new shortened url
        if (nth <= 0) return url.length();
        return urlHelper(--nth, delim, url.substring(0, url.lastIndexOf(delim)));
    }

    public static void main(String[] args){
//        novelBuilder tester = new novelBuilder("https://www.royalroad.com/fiction/21220/mother-of-learning");
//        tester.getName();
//        tester.collectChapterLinks();



        //tests to ensure we get enough chapters
//        System.out.println(tester.chapterUrls.size());
//        System.out.println(tester.chapterTotal);
        //we still have a bug after the 2nd click that skips to last page but whatever for now
        //a final bonus would be to use multiple workers
        //TODO: fix chapter skip bug, make modifyBrowser work


    }

}

