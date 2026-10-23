# Security Policy

## Supported Versions

We actively support the following versions with security updates:

| Version | Supported |
|---------|-----------|
| 1.x.x   | ✅ Yes    |
| < 1.0.0 | ❌ No     |

## Reporting a Vulnerability

We take security seriously. If you discover a security vulnerability, please report it responsibly.

### How to Report

**Email**: security@anti-brainrot.app

Include:
- Description of the vulnerability
- Steps to reproduce
- Potential impact
- Affected versions
- Suggested fix (if any)

### What to Expect

1. **Acknowledgment** within 48 hours
2. **Initial assessment** within 7 days
3. **Regular updates** on progress
4. **Coordinated disclosure** after fix is released

### Disclosure Timeline

- **Critical**: Fix within 7 days, disclose after 30 days
- **High**: Fix within 14 days, disclose after 45 days
- **Medium**: Fix within 30 days, disclose after 60 days
- **Low**: Fix in next release, disclose after 90 days

We credit reporters in release notes (unless anonymity requested).

## Security Architecture (Offline-First)

### Data Protection

| Layer | Implementation |
|-------|---------------|
| **Transport** | **No network calls** - zero attack surface |
| **Local Storage** | Room (SQLite) with Android's built-in encryption |
| **Memory** | No sensitive data in logs, no WebViews |
| **Inter-Process** | Signature-level permissions for services, `exported=false` by default |

### Permissions

- **Minimum Required**: Only request permissions essential for offline features
- **Runtime Requests**: Explain purpose before requesting
- **User Control**: Easy revocation via system settings
- **No Network Permissions**: `INTERNET` permission **not declared**
- **No Location**: Never request `ACCESS_FINE/COARSE_LOCATION`

### Accessibility Service

- **Restricted Scope**: Only monitors supported social media packages (declared in `<queries>`)
- **No Text Reading**: Does not read message content, passwords, or private data
- **On-Device Processing**: All detection happens locally
- **User Consent**: Explicit enable in system settings required
- **No Data Exfiltration**: Cannot send data anywhere (no internet permission)

### No Billing/Subscription System

- **No RevenueCat**, **No Google Play Billing**, **No Razorpay**
- **No Purchase Validation**: No server-side verification needed
- **No Payment Data**: Zero financial data handled

### Network Security

**Not applicable** - the app declares **no `INTERNET` permission** and makes **zero network calls**.

```xml
<!-- No network_security_config.xml needed -->
<!-- No domains to pin, no APIs to call -->
```

### Code Security

- **R8/ProGuard**: Full obfuscation in release builds
- **No Debug Code**: `BuildConfig.DEBUG` guards all debug features
- **Dependency Scanning**: Automated via GitHub Dependabot
- **Static Analysis**: Detekt, Lint in CI
- **Secrets Detection**: TruffleHog in pre-commit and CI (though no secrets exist)
- **Minimal Dependencies**: Reduced attack surface

## Threat Model

### Assets to Protect
1. User's reel counting data (behavioral patterns - local only)
2. App integrity (anti-tamper)

### Threat Actors
- Malicious apps on same device
- Reverse engineers
- Disgruntled users

### Mitigations

| Threat | Mitigation |
|--------|------------|
| Local data access | Android sandbox, no exported providers with sensitive data |
| Reverse engineering | R8 obfuscation, no critical logic in Java/Kotlin bytecode |
| Malicious app interference | Signature-level permissions, restricted queries |

## Secure Development Practices

### Dependencies
- Pin exact versions (no dynamic `+`)
- Verify checksums in CI
- Update regularly (Dependabot PRs)
- Audit new dependencies (license, maintenance, vulnerabilities)
- **No network libraries** (Retrofit, OkHttp, Firebase, etc.)

### Code Review
- Security-focused review for accessibility service, file providers
- No secrets in code (none needed)
- Input validation on all user inputs
- Principle of least privilege

### Testing
- Accessibility service edge cases
- Permission denial handling
- Database migration testing
- Widget update reliability

## Incident Response

Since there's no backend infrastructure:
1. **Detect** - User reports, static analysis
2. **Assess** - Impact analysis (local data only)
3. **Remediate** - Hotfix release via GitHub
4. **Notify** - GitHub release notes, discussions
5. **Learn** - Postmortem in issue tracker

## Compliance

- **GDPR**: Data minimization (zero collection), user rights (local control)
- **CCPA**: No sale of data (no data to sell), deletion (uninstall)
- **Play Console**: Data safety form - "No data collected"
- **Export Controls**: No encryption export restrictions (no network crypto)

## Security Checklist for Releases

- [ ] All dependencies updated
- [ ] No new high/critical CVEs
- [ ] ProGuard/R8 mapping uploaded
- [ ] **`INTERNET` permission NOT declared** (verify in manifest)
- [ ] Permissions audit completed
- [ ] Secrets scan passed (trivial - no secrets)
- [ ] Accessibility service scope verified
- [ ] File provider paths verified
- [ ] Privacy policy updated

## Contact

**Security Team**: security@anti-brainrot.app  
**PGP Key**: [Available on request]  
**Bug Bounty**: Not currently offered, but responsible disclosure recognized

---

*This policy is reviewed quarterly. Last review: 2024-01-15*