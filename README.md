# repoReader

## Synopis
A simple REST service which will return details of given Github repository. Response include:
- full name of repository
- description of repository
- git clone url
- number of stargazers
- date of creation formatted in requesters local date format

- The API of the service look as follows:

Endpoint:
```GET /repositories/{owner}/{repositoryname}```
Response example:
```
{
    "fullName": "KamilKafara/test_repo",
    "description": "Test repository for integration test",
    "cloneUrl": "https://github.com/KamilKafara/test_repo.git",
    "stars": 0,
    "createdAt": "2023-03-24 23:06"
}
```

---
##  How to start:

### Build the project:

`mvn package`

### Use variable in properties file
application.properties contains Github token

```properties
github.authorization.token=place_to_your_github_token
```

**You need rebuild project after change it**

# API Reference

- GitHub API reference can be found at: https://docs.github.com/en/rest
- How create a personal access token: https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token

# Tests*

`mvn test`

 *e2e test will pass successful when application get a right Github token
