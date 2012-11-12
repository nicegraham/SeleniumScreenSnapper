Takes screenshots of the browser and highlights WebElements.

You can highlight a single weblement in the browser or a bunch of them.

`driver.get("http://www.google.com");  
 WebElement searchbox = driver.findElement(By.name("q"));  
 screenSnapper.screenshot(searchbox);  
 `

 Screenshot:
 ![Searchbox highlighted](https://raw.github.com/nicegraham/SeleniumScreenSnapper/master/images/1352712088481.png)
 thumbnail:
 ![Searchbox highlighted](https://raw.github.com/nicegraham/SeleniumScreenSnapper/master/images/thumbnails/1352712088481.png)

 `searchbox.sendKeys("cheese");
 WebElement searchButton = wait.until(visibilityOfElementLocated(By.name("btnG")));
 searchButton.click();
 wait.until(visibilityOfElementLocated(By.className("navend")));
 List<WebElement> results = driver.findElements(By.className("g"));
 screenSnapper.screenshot(results);`

 Screenshot:
 ![Searchbox highlighted](https://raw.github.com/nicegraham/SeleniumScreenSnapper/master/images/1352712091765.png)
 thumbnail:
 1
 ![Searchbox highlighted](https://raw.github.com/nicegraham/SeleniumScreenSnapper/master/images/thumbnails/1352712091765_1.png)
 2
 ![Searchbox highlighted](https://raw.github.com/nicegraham/SeleniumScreenSnapper/master/images/thumbnails/1352712091765_2.png)
 etc.