## MPIN OTP Android
MPIN OTP Android is an android library built for generating OTP token. This library will be used by Partner Application that integrate to Bank Open API and it will be used for every transaction that require MPIN. The token will be sent through the payload of API Request to Service and will be validated in OTP Service.

## Gradle
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```
**Step 2.** Add the dependency
```
dependencies {
	    implementation 'com.github.Dwidasa-123:mpin-otp-android:v1.0.0'
}
```

## Usage
### Otp
`Otp` is an final class that only has access to these two static methods.
```kotlin
    Otp.activateOtp(this, deviceId, code, pin);
    Otp.generateToken(context, pin);
```

### Otp.activateOtp
This method is used to activate or register the OTP to Partner Mobile Application. It has four parameters as follow :
- **context**: It takes the context value from Android context
- **deviceId**: Partner Mobile Application should provide the device id that unique for each device.
- **code**: Partner Mobile Application should generate and provide 6 digits random number, this code may also be required when make binding account to Open API
- **pin**: PIN is Pass Code for Partner Mobile Application that user has to create during binding account to Open API and also will be used for every transaction to generate the token.

### Otp.generateToken
This method is used to generate token that will be used for every transaction, It returns 8 digits random number as token. It has two parameters as follow :
- **context**: It takes the context value from Android context
- **pin**: PIN is Pass Code for Partner Mobile Application that user has been created during binding account to Open API and also will be used for every transaction to generate the token.
