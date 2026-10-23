# Anti-BrainRot - Reels & Shorts Tracker (Fully Offline)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-8.0+-green.svg)](https://developer.android.com/about/versions/oreo)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-8.0+-green.svg)](https://gradle.org)
[![Offline](https://img.shields.io/badge/100%25-Offline-brightgreen.svg)]()

**Anti-BrainRot** is a **fully offline**, open-source Android application that helps you regain control over your time by tracking and limiting your reels/shorts consumption across social media platforms like Instagram Reels, YouTube Shorts, TikTok, Snapchat, and more.

**No internet connection required. No accounts. No tracking. No analytics. No ads. Complete privacy.**

## Features

### 🎯 Core Features (All Offline)
- **Real-time Reels Counter** - Tracks reels watched across all supported apps using Accessibility Service
- **Floating Bubble Overlay** - See your reel count live while using other apps
- **Home Screen Widgets** - Multiple widget sizes (compact, expanded, buddy) to monitor usage at a glance
- **Smart Blocking** - Block reels/shorts after reaching your daily limit
- **Usage Statistics** - Detailed hourly/daily/weekly analytics with visual charts (stored locally)
- **Reels Wrapped** - Weekly/monthly recap of your scrolling habits

### 🧠 Behavioral Features
- **Onboarding Assessment** - Personalized questions to understand your scrolling patterns
- **Daily Limits** - Set custom daily reel limits with local notifications
- **Pace Nudges** - Gentle reminders when you're scrolling too fast
- **Milestone Alerts** - Notifications at key thresholds (25, 50, 100+ reels)
- **Night Protection** - Automatic blocking during sleep hours
- **Roasting** - Fun motivational messages every 50 reels

### 🔒 Privacy First
- **Zero Network Calls** - No analytics, no crash reporting, no telemetry
- **No Accounts** - No login, no cloud sync, no user tracking
- **Local Storage Only** - All data stays on your device (Room/SQLite)
- **No Ads** - Completely ad-free experience
- **Open Source** - Full transparency, audit the code yourself

## Supported Platforms

| Platform | Package Name | Detection Method |
|----------|--------------|------------------|
| Instagram Reels | `com.instagram.android` | Accessibility + Usage Stats |
| Instagram Lite | `com.instagram.lite` | Accessibility + Usage Stats |
| YouTube Shorts | `com.google.android.youtube` | Accessibility + Usage Stats |
| TikTok | `com.zhiliaoapp.musically` | Accessibility + Usage Stats |
| TikTok Lite | `com.ss.android.ugc.trill` | Accessibility + Usage Stats |
| Snapchat | `com.snapchat.android` | Accessibility + Usage Stats |
| Facebook Reels | `com.facebook.katana` | Accessibility + Usage Stats |
| Facebook Lite | `com.facebook.lite` | Accessibility + Usage Stats |
| WhatsApp Status | `com.whatsapp` | Usage Stats |

## Architecture

```
app/
├── src/main/
│   ├── java/com/brainrot/android/
│   │   ├── core/                 # Base classes, DI, navigation
│   │   ├── feature_battle/       # Local battle mode (offline)
│   │   ├── feature_fallback/     # Fallback counter (Usage Stats)
│   │   ├── feature_feedback/     # Local feedback (no network)
│   │   ├── feature_invite/       # Local invite sharing
│   │   ├── feature_pace_nudge/   # Scroll pace detection
│   │   ├── feature_permission/   # Permission management
│   │   ├── feature_reels_counter/# Core counting logic
│   │   ├── feature_stats/        # Analytics, charts, wrapped (local)
│   │   ├── feature_weekly_story/ # Weekly recap stories
│   │   ├── feature_widget/       # Glance app widgets
│   │   ├── floating_bubble/      # Overlay service
│   │   ├── navigation/           # Navigation graph, deep links
│   │   ├── services/             # Accessibility, foreground services
│   │   └── workers/              # WorkManager background tasks
│   ├── res/                      # Resources (layouts, strings, drawables)
│   └── assets/                   # Fonts, configs
├── build.gradle.kts              # App-level build config (offline deps only)
└── proguard-rules.pro            # R8/ProGuard rules
```

## Tech Stack (Offline Only)

- **Language**: Kotlin 1.9+
- **UI**: Jetpack Compose (Material3), Glance for widgets
- **Architecture**: MVVM, Clean Architecture, Unidirectional Data Flow
- **DI**: Hilt (Dagger)
- **Async**: Coroutines, Flow, StateFlow
- **Database**: Room (SQLite) - Local only
- **Serialization**: Kotlinx Serialization (JSON for local storage)
- **Background**: WorkManager
- **Preferences**: DataStore
- **Testing**: JUnit, MockK, Compose Testing, Espresso
- **CI/CD**: GitHub Actions

## Requirements

- Android 8.0 (API 26) or higher
- Accessibility Service permission (for reel detection)
- Usage Stats permission (fallback counting)
- Display over other apps permission (for floating bubble)
- Notifications permission (for daily reports)

**No internet permission required.**

## Building from Source

### Prerequisites

- JDK 17+
- Android SDK (API 34)
- Android Studio Ladybug+ or command line tools

### Clone and Build

```bash
git clone https://github.com/yourusername/anti-brainrot.git
cd anti-brainrot

# Debug build (APK)
./gradlew assembleDebug

# Release build (APK, requires signing config)
./gradlew assembleRelease
```

### Configuration

Create `local.properties` with your SDK path:
```properties
sdk.dir=/path/to/Android/Sdk
```

For release builds, add signing config to `local.properties` or `gradle.properties`:
```properties
storeFile=../keystore.jks
storePassword=***
keyAlias=***
keyPassword=***
```

**No API keys needed - fully offline!**

## Project Structure Details

### Core Modules

| Module | Description |
|--------|-------------|
| `core` | Base activity, DI modules, navigation setup, common utilities |
| `feature_reels_counter` | Accessibility service, usage stats, reel detection logic |
| `feature_stats` | Room database, statistics calculation, charts |
| `feature_widget` | Glance-based home screen widgets |
| `floating_bubble` | System alert window overlay service |
| `services` | Foreground services, boot receivers |

### Key Classes

- `ReelsAccessibilityService` - Main accessibility service for reel detection
- `FallbackReelsCounterService` - Usage stats fallback when accessibility unavailable
- `ReelsCounterFloatingService` - Floating bubble overlay
- `AppNavigationViewModel` - Navigation state management
- `StatsRepository` - Data layer for statistics (local Room DB)

## Contributing

We welcome contributions! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

### Development Setup

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make your changes
4. Run tests: `./gradlew test`
5. Run lint: `./gradlew lint`
6. Submit a Pull Request

### Code Style

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use `ktlint` for formatting: `./gradlew ktlintFormat`
- Write tests for new features
- Update documentation as needed

## Privacy & Security

See [PRIVACY.md](PRIVACY.md) and [SECURITY.md](SECURITY.md) for details.

**TL;DR**: This app collects **zero** data. No analytics, no crash reports, no network calls. Ever.

## Permissions Explained

| Permission | Purpose | Required |
|------------|---------|----------|
| `FOREGROUND_SERVICE` | Background counting | Yes |
| `FOREGROUND_SERVICE_SPECIAL_USE` | Special use foreground service | Yes |
| `PACKAGE_USAGE_STATS` | Fallback counting | Yes |
| `POST_NOTIFICATIONS` | Daily reports, alerts | Yes |
| `RECEIVE_BOOT_COMPLETED` | Auto-start services | Yes |
| `BIND_ACCESSIBILITY_SERVICE` | Primary reel detection | Yes |
| `SYSTEM_ALERT_WINDOW` | Floating bubble overlay | Yes |
| `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS` | Keep services alive | Yes |
| `VIBRATE` | Haptic feedback | No |

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Icons from [Material Design Icons](https://github.com/google/material-design-icons)
- Inspiration from digital wellbeing research

## Disclaimer

This app is not affiliated with, endorsed by, or connected to Instagram, YouTube, TikTok, Snapchat, Facebook, or any other social media platform. All trademarks belong to their respective owners.

---

**Made with ❤️ for digital wellbeing - 100% Offline, 100% Private**

[Report Bug](https://github.com/yourusername/anti-brainrot/issues) · [Request Feature](https://github.com/yourusername/anti-brainrot/issues) · [Join Community](https://github.com/yourusername/anti-brainrot/discussions)