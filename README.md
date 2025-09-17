# Pokédex App

[![MIT](https://img.shields.io/badge/License-MIT%20-blue.svg)](https://github.com/shawn-mperfinan/pokedex-android?tab=MIT-1-ov-file#MIT-1-ov-file)
[![Deployment Build](https://github.com/shawn-mperfinan/pokedex-android/actions/workflows/build_deployment.yml/badge.svg)](https://github.com/shawn-mperfinan/pokedex-android/actions/workflows/build_deployment.yml)
[![GitHub Releases](https://img.shields.io/github/v/release/shawn-mperfinan/pokedex-android)](https://github.com/shawn-mperfinan/pokedex-android/releases)
[![GitHub](https://img.shields.io/badge/GitHub-Profile-black?&logo=github)](https://github.com/shawn-mperfinan)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?&logo=linkedin)](https://www.linkedin.com/in/michael-shawn-perfinan/)

A modern Pokédex built with Android, showcasing Pokémon data, stats, and abilities. Designed with a clean architecture, Kotlin, and Jetpack libraries for a smooth and responsive experience.

### 🚀 Release & Distribution Strategy

This project demonstrates a professional-grade CI/CD pipeline:

- **Development Builds**
    - Delivered via Firebase App Distribution
    - Installed by testers using invite links
    - Triggered with: `bundle exec fastlane deploy_dev`

> NOTE: Live Deployment will be configured soon once Google Play account is created.

[//]: # (- **Live Builds**)

[//]: # (    - Uploaded to Google Play Console &#40;Internal Track&#41;)

[//]: # (    - GitHub Actions also generates release tags with downloadable APK)

[//]: # (    - Triggered with: `bundle exec fastlane deploy_live`)

[//]: # ()
[//]: # (> Note: Google Play release is internal-only &#40;not public&#41;.  )
