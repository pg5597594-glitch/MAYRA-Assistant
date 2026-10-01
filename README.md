# MAYRA Android Voice Assistant

Native Kotlin + Jetpack Compose starter project for a voice-first MAYRA assistant.

## Architecture

- Kotlin
- Jetpack Compose
- Android Foreground Service
- Gemini Live API integration point
- 16 kHz PCM audio pipeline integration point
- No text-chat UI
- GitHub Actions APK build

## Gemini model

The requested model is:

`gemini-3.1-flash-live-preview`

Google currently lists this as a legacy preview Live model and recommends newer Live models for new production workloads. Verify the current model availability before release.

## Security

Never commit a real Gemini API key into this repository.

For production, use a backend that issues short-lived/ephemeral credentials and keep the permanent API key server-side.

## Build

Use Android Studio or GitHub Actions.

The generated APK should be available from the Actions artifact after a successful build.

## Important

This repository is a buildable Android foundation, not a claim that background microphone access or Gemini authentication can bypass Android OS restrictions. Android requires microphone permission and foreground-service rules.