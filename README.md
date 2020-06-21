
## This Project is to learn Web automation using Selenium webdriver with Java.

The course I am following is 
[Selenium WebDriver with Java -Basics to Advanced+Frameworks](https://www.udemy.com/course/selenium-real-time-examplesinterview-questions/)

##### Different ways to locate element
    
    id
    name
    class name
    linktext
    CSS selector
    xpath

#### All the CSS selector details are available [here](http://pragmatictestlabs.com/2020/02/09/mastering-css-for-selenium-test-automation-2/)

##### Different ways of writing Xpath  
* Single attribute `//input[@id='Email']`
* Parent `//div[@class='form-group']//parent::div`
* Ancestor `//div[@class='form-group']//ancestor::div`
* Child `//div[@class='form-group']//child::input[1]`
* Descendant `//div[@class='form-group']//descendant::a`

* Following 
```
//div[@class='form-group']//following::input
//div[@class='form-group']//following-sibling::div 
```
* Preceding
```
//div[@class='form-group']//preceding::input
//div[@class='form-group']//preceding-sibling::div
```
* index based
```
//input[@class="desktop-input"][2]
//input[@class="desktop-input"][last()]
//input[@class="desktop-input"][last()-1]
```
* Text value
```
//div[contains(text(),'CONTINUE')]
//div[starts-with(text(),'CONTINUE')]
//div[text()='CONTINUE']
//div[contains (.,'CONTINUE')]
```
* And/Or
```
//input[@type='submit' or @name='btnReset']
//input[@type='submit' and @name='btnLogin']
 ```
 
#### Starting Selenium grid in hub mode
 
  ```java -jar selenium-server-standalone-<version>.jar -role hub```
