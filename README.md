# slackbot chat first team homework upgrade
```
기존의 명령어타입을 map의 key로 구현하여 프로그램의 복잡성을 낮추고 코드의 가독성을 높혔다.
```
## SERVICE

### FRIEND
```
친구의 정보를 저장하는 객체
name, age, gender 멤버를 가진다.


Friend(String, int, gender)
getFriendInformation() : name, age, gender를 순차적으로 출력한다.
```
### FRIENDDATABASE 
```
친구의 정보를 저장하는 데이터베이스
hashmap<string,friend> 데이터베이스를 포함한다.


addFriendToDataBase(String friendName, int age, Friend.Gender gender)
searchAndGetFriendByName(String friendName)
removeFriendByName(String friendName)
canSaveFriendMore()
getFriendsNumber()
```
### COMMANDSERVICE
```
전달된 커맨드의 가 지정된 조건을 만족하는지 검사한다.


friendNameValidCheck(String friendName)
friendAgeValidCheck(String age)
Friend.Gender getGenderFromCommandOption(FriendDataBase friendDataBase, String genderSyntax)
```
### COMMANDPARSINGSERVICE
*@groupbot add username age gender*

*@groupbot find username*

*@groupbot list*

*@groupbot time*

```
입력된 커맨드를 분석하여 요구기능 추출
명령어의 첫 토큰을 key로 commandLists에서 찾아 실행한다.


parsingCommand(FriendDataBase friendDataBase, String command)
CommandService checkIfCommandOptionIsExist(String[] commandLines, CommandService command)
```
### ADDCOMMAND
```
friendDatabase에서 add기능을 수행한다.


executeCommand(FriendDataBase friendDataBase, String[] commandLine)
```
### FINDCOMMAND
```
friendDatabase에서 find기능을 수행한다.


executeCommand(FriendDataBase friendDataBase, String[] commandLine)
```
### REMOVECOMMAND
```
friendDatabase에서 remove기능을 수행한다.


executeCommand(FriendDataBase friendDataBase, String[] commandLine)
```
### TIMECOMMAND
```
현재 시간을 출력한다.


executeCommand(FriendDataBase friendDataBase, String[] commandLine)
```
### SLACKBOTSERVICE

### SLACKBOTAPPLICATION



## EXCEPTION
### FRIENDDATABASEALREADYFULLEXCEPTION
```
친구의 수는 10명을 초과할 수 없다.
```
### FRIENDDATABASEEMPTYEXCEPTION
```
친구의 수가 0명일때 list명령어는 실행되지 않는다.
```
### FRIENDEXISTEXCEPTION
```
검색한 친구가 데이터베이스 없을 때 명령은 실행될 수 없다.
```
### INVALIDCOMMANDSYNTAXEXCEPTION
```
입력된 명령이 정해진 조건에 만족되지 않을 때 명령은 실행될 수 없다.
```
### INVALIFRIENDAGESYNTAXEXCEPTION
```
입력된 age가 조건에 부합되지 않을 때 명령은 실행되지 실행될 수 없다.
```
### INVALIDFRIENDNAMEEXCEPTION
```
입력된 friend가 조건에 부합되지 않을 때 명령은 실행될 수 없다. 
```
### INVALIDGENDERSYNTAXEXCEPTION
```
Gender가 지정 서식(male, man, boy, female, girl, woman) 을 벗어날 경우 명령은 실행될 수 없다.
```
### NOCOMMANDOPTIONEXCEPTION
```
명령어가 지정서식을 벗어날 때 명령은 실행될 수 없다.
```
### NOTFRIENDEXISTEXCEPTION
```
명령의 대상이 되는 친구가 데이터베이스에 없을 때 명령은 실행될 수 없다.
```



## TEST
### COMMANDPARSINGSERVICETEST
```
setup()
friendDatabase엔 (name, age, gender)가 ("hangeul",11,Friend.Gender.MAN), ("hankook",21,Friend.Gender.MAN)인 샘플 2개를 삽입한다.

executeAddCommandAndReturnSuccessMessage()
add 명령어 추출 정상동작 테스트

executeTimeCommandAndReturnLocalTime()
time 명령어 추출 정상동작 테스트

executeRemoveCommandAndReturnSuccessMessage()
remove 명령어 추출 정상동작 테스트

executeFindCommandAndReturnFriendInformation() 
find 명령어 추출 정상동작 테스트

executeListCommandAndReturnFriendsListOfDatabase()
list 명령어 추출 정상동작 테스트

makeErrorWhenNoOptionToCommand()
명령어 형식이 올바르지 않을 경우 정상적인 예외처리를 하는지 테스트

makeErrorWhenNoOptionToCommandInCheckOptionIsExistMethod()
명령어 형식이 올바르지 않을 경우 정상적인 예외처리를 하는지 테스트
```
### COMMANDSERVICETEST
```
makeErrorWhenFriendNameOptionIsInvalid()
이름 형식이 올바르지 않을 경우 정상적인 예외처리를 하는지 테스트

returnStringFriendNameIfNameIsValid()
이름 형식이 올바른 경우 정상적인 동작이 되는지 테스트

makeErrorWhenFriendAgeIsInvalid()
나이 형식이 올바르지 않을 경우 정상적인 예외처리를 하는지 테스트

returnIntFriendAgeIfAgeIsValid()
나이 형식이 올바른 경우 정상적인 동작이 되는지 테스트

makeErrorWhenFriendGenderIsInvalid()
Gender 형식이 올바르지 않을 경우 정상적인 예외처리를 하는지 테스트

returnGenderEnumIfFriendGenderIsValidWord()
Gender 형식이 올바른 경우 정상적인 동작이 되는지 테스트
```
### FRIENDDATABASETEST
```
setUp()
friendDatabase엔 (name, age, gender)가 ("hangeul",11,Friend.Gender.MAN), ("hankook",11,Friend.Gender.MAN)인 샘플 2개를 삽입한다.

addAFriendToFriendDatabase()
add명령어 정상동작 테스트

makeErrorWhenTryingToAddFriendAlreadyExist()
이미 존재하는 이름의 Friend를 추가할 시 정상적인 예외처리를 하는지 테스트

makeErrorWhenTryingToSearchFriendNotExist()
존재하지 않는 이름의 Friend를 검색할 시 정상적인 예외처리를 하는지 테스트 

searchFriendByNameReturnFriendObject()
find명령어 정상동작 테스트

makeErrorWhenTryingToRemoveFriendNotExists()
존재하지 않는 이름의 Friend를 삭제할 시 정상적인 예외처리를 하는지 테스트

removeFriendByNameAndReturnFriendName()
remove명렁어 정상동작 테스트

canSaveFriendMore()
frienddatabasesize가 capacity를 초과하지 않을 시 정상적인 반환값을 출력하는지 테스트

getNumberOfFriendInDatabase()
frienddatabasesize를 정상적으로 출력하는지 테스트
```
### FRIENDTEST
```
setUp()
friendDatabase에 1개의 샘플을 넣는다

getFriendName()
getName()의 정상출력 테스트

getFriendAge()
getAge()의 정상출력 테스트

getFriendGender()
getGender()의 정상출력 테스트
```
### ADDCOMMANDTEST
```
setUp()
friendDatabase에 9개의 샘플을 넣는다.

executeAddCommandReturnMessageWithFriendName()
add명령어 정상동작 테스트

makeErrorAfterMaximumLimitOfFriendWhenAdding()
friendDatabase에 10개를 초과하는 friend가 삽입 되었을 때 정상적인 예외처리를 하는지 테스트
```
### FINDCOMMANDTEST
```
setUp()
friendDatabase엔 (name, age, gender)가 ("hangeul",11,Friend.Gender.MAN), ("hankook",21,Friend.Gender.MAN)인 샘플 2개를 삽입한다.

executeFindCommandReturnFriendInformation()
find명령어 정상동작 테스트
```
### REMOVECOMMANDTEST
```
setUp()
friendDatabase엔 (name, age, gender)가 ("hangeul",11,Friend.Gender.MAN), ("hankook",21,Friend.Gender.MAN)인 샘플 2개를 삽입한다.

executeRemoveCommandReturnMessageWithFriendName()
remove명령어 정상동작 테스트
```
### LISTCOMMANDTEST
```
setUp()
friendDatabase엔 (name, age, gender)가 ("hangeul",11,Friend.Gender.MAN), ("hankook",21,Friend.Gender.MAN)인 샘플 2개를 삽입한다.

makeErrorDatabaseEmptyWhenTryToListCommandWhenNoData()
friendDatabase에 friend가 없는 상태에서 list명령어 실행 시 정상적인 예외처리를 하는지 테스트

executeListCommandAndReturnFriendInformationByRows()
list 명령어의 정상동작 테스트
```
### TIMECOMMANDTEST
```
executeTimeCommandReturnCurrentLocalTime()
time명령어 정상동작 테스트
```
