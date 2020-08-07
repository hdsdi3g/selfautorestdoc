# REST API
## Summary

 - [Delete Demo **DELETE** /serverPath/deleteActionControllerPath](#delete-demo-delete-/serverpath/deleteactioncontrollerpath)
 - [Get Demo **GET** /serverPath/getActionController/{textValueVarName}/path?numValue=0](#get-demo-get-/serverpath/getactioncontroller/{textvaluevarname}/pathnumvalue=0)
 - [Patch Demo **PATCH** /serverPath/patchActionControllerPath](#patch-demo-patch-/serverpath/patchactioncontrollerpath)
 - [Post Demo **POST** /serverPath/postActionControllerPath](#post-demo-post-/serverpath/postactioncontrollerpath)
 - [Put Demo **PUT** /serverPath/putActionControllerPath](#put-demo-put-/serverpath/putactioncontrollerpath)


## Delete Demo
**DELETE** /serverPath/deleteActionControllerPath

deleteActionController

Headers: 

```javascript
Response: "application/json" {
}
```

_Mandatory rights: rightForDelete | alternateRightForDelete_

[Go to the top](#rest-api) &bull; [DemoRestController :: deleteActionController](/blob/master/src/main/java/tv/hd3g/selfautorestdoc/demo/DemoRestController.java#73)

## Get Demo
**GET** /serverPath/getActionController/{textValueVarName}/path?numValue=0

A comment for get demo

Parameters:
 - **textValueVarName** String

Headers: 

```javascript
Response: "application/json" {
    links: Links,
    subInputDto: SubOutputDto,
    subList: [String, ...],
    subMap: {

    },
    textValue: String
}
```

[Go to the top](#rest-api) &bull; [DemoRestController :: getActionController](/blob/master/src/main/java/tv/hd3g/selfautorestdoc/demo/DemoRestController.java#59)

## Patch Demo
**PATCH** /serverPath/patchActionControllerPath

patchActionController

Headers: 

```javascript
Response: "application/json" {
}
```

_Mandatory rights: rightForPatch & anotherRightForPatch_

[Go to the top](#rest-api) &bull; [DemoRestController :: patchActionController](/blob/master/src/main/java/tv/hd3g/selfautorestdoc/demo/DemoRestController.java#79)

## Post Demo
**POST** /serverPath/postActionControllerPath

postActionController

Headers: 

```javascript
Request body data: "text/xml" {
    subInputDto: SubInputDto,
    subList: [String, ...],
    subMap: {

    },
    text: String
}
```

```javascript
Response: "application/json" {
    links: Links,
    subInputDto: SubOutputDto,
    subList: [String, ...],
    subMap: {

    },
    textValue: String
}
```

[Go to the top](#rest-api) &bull; [DemoRestController :: postActionController](/blob/master/src/main/java/tv/hd3g/selfautorestdoc/demo/DemoRestController.java#49)

## Put Demo
**PUT** /serverPath/putActionControllerPath

putActionController

Headers: 

```javascript
Response: "application/json" {
}
```

[Go to the top](#rest-api) &bull; [DemoRestController :: putActionController](/blob/master/src/main/java/tv/hd3g/selfautorestdoc/demo/DemoRestController.java#66)
