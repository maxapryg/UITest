User can see another profile


Scenario: Birthday privacy

Meta:
@categories profile useful

Given logged Profile page with setting <setting>
Then  user <user1> can see another birthday
Then user <user2> can't see another user birthday

Examples:
|user1         |user2         |setting       |
|logged        |null          |everybody     | 
|unlogged      |null          |everybody     | 
|friend        |not_friend    |friends       |
|logged        |unlogged      |registered    |
|null          |logged        |nobody        |
|null          |unlogged      |nobody        |




Scenario: School privacy

Meta:
@categories profile useful
Given logged Profile page with setting <setting>
Then user <user1> can see school
Then user <user2> can't see school 

Examples:
|user1         |user2       |setting   |
|logged        |null        |everybody |
|unlogged      |null        |everybody |
|logged        |unlogged    |friends   |
|null          |logged      |nobody    |
|null          |unlogged    |nobody    |






Scenario: Show email

Meta:
@categories profile useful

Given logged Profile page with settings <show> and <display>
Then user <user1> can see email
Them user <user3> can't see email

Example:
|user1          |user2            |show     |display   |
|logged         |                 |true     |everybody |
|unlogged       |                 |true     |everybody |
|logged         |unlogged         |true     |registered|
|friend         |not_friend       |true     |friends   |
|null           |unlogged         |true     |friends   |
|null           |logged           |true     |nobody    |
|null           |unlogged         |true     |nobody    |
|null           |logged           |false    |everybody |
|null           |unlogged         |false    |everybody |
|null           |logged           |false    |registered|
|null           |unlogged         |false    |registered|
|null           |logged           |false    |friends   |
|null           |unlogged         |false    |friends   |
|null           |logged           |false    |nobody    |
|null           |unlogged         |false    |nobody    |




Scenario: Show list of friends

Meta:
@categories profile useful

Given logged Profile page with friend's checkbox <friend>
Then user <user1> can see list of friends
Then user <user2> can't see list of friends

Example:
|user1         |user2    |friend|
|logged        |null     |on    |
|unlogged      |null     |on    |
|null          |logged   |off   |
|null          |unlogged |off   |