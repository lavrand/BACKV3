## API CVBANK *(All tests performed in Postman)*

### User Sign Up
Method **POST**

<http://localhost:8080/api/signup>

During signup the next JSON in the body should be sent, see example below:
```json
{"username": "donald", "password": "trump"}
```
***
In case of successful sign up the next reply will be received: 
```json
{
    "timestamp": "09/01/2018 17:06:28",
    "status": "success",
    "code": "201",
    "message": "New user with id 666 and username donald has been signed up",
    "data": "666 donald"
}
```

Username should be unique, don't use the same username during testing several times, other way you wouldn't be able to sign up with the next JSON reply:
```json
{
    "timestamp": "2018-09-01T14:03:32.418+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "could not execute statement; SQL [n/a]; constraint [unq_username]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
    "path": "/api/signup"
}
```
### Login
Method **POST**
 
<http://localhost:8080/api/login>

**Header**

Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkb25hbGQiLCJleHAiOjE1MzU2NjEyNjR9.eESH592ehteRXdUmsF79SwV0aApwp9kt7xKNNdM6mSE

**Body**  

```json
{"usernameOrEmail": "donald", "password": "trump"}
```
In case of successful login the next reply will be received: 
```json
{
    "timestamp": "09/02/2018 15:05:27",
    "status": "success",
    "code": "200",
    "message": "New user with username donald has been successfully logged in",
    "data": {
        "username": "donald",
        "usertype": "[ROLE_ADMIN]",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDaHVwYWthYnJhIiwiZXhwIjoxNTM1ODkxNzI3fQ.eC4E_dlYCI_tkWRnKfruPynQhne-gfgEhYno0zUXRkU"
    }
}
```

Token in field code and username in field data to keep constant standard Response.

***

### Logout

Method **GET**

<http://localhost:8080/api/logout>

In case of successful log out the next reply will be received: 
```json
{
    "timestamp": "09/01/2018 18:02:40",
    "status": "success",
    "code": "200",
    "message": "Logout done",
    "data": null
}
```
***

### CRUD on Profiles

### Get All Profiles
Method **GET**

<http://localhost:8080/api/profiles>

***

### GET Profile by Id
Method **GET**

<http://localhost:8080/api/profiles/{id}>

***

### Create New Profile
Method  **POST**
 
<http://localhost:8080/api/profiles>

```json
   {
        "username": "DELL_ISRAEL",
        "user_type": "company",
        "languages": "English, Hebrew",
        "residence": "BeerSheva",
        "linkedin": "-",
        "phone": "+972 73 243 4233",
        "birthday": 19778400000,
        "company_name": "DELL ISRAEL",
        "password": "DELL321",
        "website": "www.DELLcom",
        "country": "Israel",
        "city_town": "BeerSheva",
        "street": "Yeelim Street",
        "house_building": "13",
        "postcode": "678654",
        "position": "Technical Manager",
        "lastName": "Eric",
        "firstName": "Cartman",
        "email": "Eric.Cartman@southpark.com"
    }
```
 
***

### Update existing Profile
Method **PUT**

<http://localhost:8080/api/profiles/{id}>

```json
{
        "id_user": 4,
        "username": "DELL_ISRAEL",
        "user_type": "company",
        "languages": "English, Hebrew",
        "residence": "BeerSheva",
        "linkedin": "-",
        "phone": "+972 73 243 4233",
        "lastName": "Eric",
        "firstName": "Cartman",
        "email": "Eric.Cartman@southpark.com",
        "birthday": 19778400000,
        "company_name": "DELL ISRAEL",
        "password": "DELL321",
        "website": "www.DELLcom",
        "country": "Israel",
        "city_town": "BeerSheva",
        "street": "Yeelim Street",
        "house_building": "13",
        "postcode": "678654",
        "position": "Technical Manager"
    }

```
    
***    
    
### Delete existing Profile by Id
Method **Delete**

<http://localhost:8080/api/profiles/{id}>

***

### Delete All Profiles
Method **Delete**

<http://localhost:8080/api/profiles/>


***

### GET Skills directory
Method **GET**

<http://localhost:8080/api/skills/>

```json

[
        {
            "id": 1,
            "nameSkill": "Java"
        },
        {
            "id": 2,
            "nameSkill": "PHP"
        },
        ...
    ]

```

***

### GET Languages directory
Method **GET**

<http://localhost:8080/api/languages/>

```json

[
        {
            "id": 2,
            "nameLang": "English"
        },
        {
            "id": 4,
            "nameLang": "France"
        },
        ...
    ]

```

***

### GET Folders (return only folders without cv)
Method **GET**

<http://localhost:8080/api/folders>

```json
[
	{
		"id": 1,
		"nameFolder": "CV's Junior C#",
		"cv": null
	},
	{
		"id": 2,
		"nameFolder": "CV's Middle Javascript",
		"cv": null
	},
	...
	
]

```

***

### GET Folders (return all folders and all cv)
Method **GET**

<http://localhost:8080/api/folders/cv>

```json
[
        {
            "id": 1,
            "nameFolder": "CV's Junior C#",
            "cv": [
                {
                    "id": 4,
                    "firstName": "Liam",
                    "lastName": "Smith",
                    "summary": "I'm mega coder",
                    "about": "I'm best of the best",
                    "email": "John.Lenin@bigmail.com",
                    "phone": "+972 73 243 4222",
                    "residence": "",
                    "birthday": "1992-02-29",
                    "linkedin": "",
                    "position_preference": null,
                    "github": "",
                    "recommendations": "",
                    "title": "",
                    "activated": true,
                    "views": 0,
                    "portfolio": "",
                    "preferencedArea": "",
                    "salaryFromPreference": 300,
                    "salaryTillPreference": 1000,
                    "cvActivity": [],
                    "education": [],
                    "template": null,
                    "languages": [],
                    "skills": []
                }
            ]
        },
        {
            "id": 2,
            "nameFolder": "CV's Middle Javascript",
            "cv": [
                {
                    "id": 2,
                    "firstName": "Joe",
                    "lastName": "Rodriguez",
                    "summary": "I'm mega coder",
                    "about": "I'm best of the best",
                    "email": "Rodriguez@bigmail.com",
                    "phone": "+972 73 243 4222",
                    "residence": "",
                    "birthday": "1986-05-21",
                    "linkedin": "",
                    "position_preference": null,
                    "github": "",
                    "recommendations": "",
                    "title": "",
                    "activated": true,
                    "views": 0,
                    "portfolio": "",
                    "preferencedArea": "",
                    "salaryFromPreference": 300,
                    "salaryTillPreference": 1000,
                    "cvActivity": [],
                    "education": [],
                    "template": null,
                    "languages": [],
                    "skills": []
                }
            ]
        }
    ]

```
***

### Add Folders (return new folder object)
Method **POST**

<http://localhost:8080/api/folders>

```json

	{
		"nameFolder": "CV's Middle Java"        
	}

```
***
### Change folder name
Method **PUT**

<http://localhost:8080/api/folders/{id}>

```json

	{
		"nameFolder": "CV's Senior Java"         
	}

```
***
### Delete folder
Method **DELETE**

<http://localhost:8080/api/folders/{id}>

***

### GET Folder by id (return folder with cv)
Method **GET**

<http://localhost:8080/api/folders/{id}/cv>

```json

	{
		"id": 1,
		"nameFolder": "CV's Junior C#",
		"cv": [
			{
				"id": 4,
				"firstName": "Liam",
				"lastName": "Smith",
				"summary": "I'm mega coder",
				"about": "I'm best of the best",
				"email": "John.Lenin@bigmail.com",
				"phone": "+972 73 243 4222",
				"residence": "",
				"birthday": "1992-02-29",
				"linkedin": "",
				"position_preference": null,
				"github": "",
				"recommendations": "",
				"title": "",
				"activated": true,
				"views": 0,
				"portfolio": "",
				"preferencedArea": "",
				"salaryFromPreference": 300,
				"salaryTillPreference": 1000,
				"cvActivity": [],
				"education": [],
				"template": null,
				"languages": [],
				"skills": []
			}
		]
	}

```

***

