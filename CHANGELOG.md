# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Initial open source release (fully offline)
- Core reel counting via Accessibility Service
- Fallback counter using Usage Stats
- Floating bubble overlay
- Home screen widgets (Glance)
- Daily/weekly statistics with charts (local Room DB)
- Reels Wrapped weekly recap
- Local battle mode (offline friend challenges)
- Onboarding flow with personalized assessment
- Pace nudges and milestone notifications
- Multi-language support (EN, HI, ID, DE, ES, FR, etc.)
- 100% offline - no network dependencies

### Removed
- Firebase Analytics, Crashlytics, Performance
- Firebase Auth, Cloud Messaging, Firestore
- RevenueCat subscription system
- Google Play Billing
- Razorpay payment integration
- Facebook SDK
- Retrofit/OkHttp networking
- Coil image loading (network)
- All `INTERNET` permission usage
- Cloud sync functionality
- User accounts/authentication
- Analytics/telemetry collection

### Security
- Zero network attack surface
- No third-party SDKs with data collection
- Minimal permission set

## [1.0.0] - 2024-01-15

### Added
- First stable offline release
- All core features implemented offline
- Production-ready build configuration
- Comprehensive documentation

---

## Release Template

### [Version] - YYYY-MM-DD

### Added
- New features (offline only)

### Changed
- Changes in existing functionality

### Deprecated
- Soon-to-be removed features

### Removed
- Removed features (especially online dependencies)

### Fixed
- Bug fixes

### Security
- Vulnerability fixes