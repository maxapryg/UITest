Scenario: Subject with correct html tags
Meta: 
@categories create_edit_post subject comfortable test
@issue LJSUP-21525

Given logged user <name> on Create Post page
When user create new post with subject <subject>
Then the post in journal has subject <subject> with correct tag

Examples:
|name         |subject     |
|testautotest |<b>Тема</b> |
|--testautotest |<i>Тема</i> |
|--testautotest |<u>Тема</u> |
|--testautotest |<s>Тема</s> |



Scenario: Subject with cropped html tags
Meta: 
@categories create_edit_post subject comfortable test

Given logged user <name> on Create Post page
When user create new post with subject <subject>
Then the post in journal has subject <subject> with cropped tag 

Examples:
|name         |subject                                                  |
|testautotest |<span style="font-size: 0.7em">Тема размер шрифта</span> |
|testautotest |<span style="color: #ad5e5e;">Тема цветная</span>        |
|testautotest |<a href="http://www.yandex.ru/">Ссылка</a>               |



Scenario: Subject with incorrect html tags
Meta: 
@categories create_edit_post subject1 comfortable

Given logged user <name> on Create Post page
When user create new post with subject <subject>
Then the post in journal has subject <cultivated_subject>

Examples:
|name         |subject        |
|testautotest |<lj-like />|
|testautotest |<img src="http://ic.pics.livejournal.com/maxapryg/71384044/504856/504856_900.jpg" alt="shop.jpg" title="shop.jpg">|
|testautotest |<iframe data-link="http://vc.videos.livejournal.com/index/player?player=new&record_id=231160&ad_template_id=4584" data-thumbnail="http://st1.eaglecdn.com/lj/20150320/550c2759a976f_1_360.jpg" frameBorder="0" height="315" src="http://vc.videos.livejournal.com/index/player?player=new&record_id=231160&ad_template_id=4584" width="560"></iframe>|
|testautotest |<lj-spoiler></lj-spoiler>|
|testautotest |<lj-map url="http://maps.yandex.ru/?ll=37.625207%2C55.699252&spn=0.022016%2C0.006677&z=16&l=map" width="425" height="350" />|
|testautotest |<lj-cut></lj-cut>|
|testautotest |<br /><lj-pi>123</lj-pi><br /></lj-pq><br /></lj-poll>|
|testautotest |<lj user='maxa'/>                                        |