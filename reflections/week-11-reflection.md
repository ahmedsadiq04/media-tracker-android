# Week 11 Reflection

**Name:** Sadiq Ahmed

**Date:** 7/30/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:** [Branch](https://github.com/ahmedsadiq04/media-tracker-android/tree/week-11) [Pull-Request](https://github.com/ahmedsadiq04/media-tracker-android/pull/12)

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Nicholas
**Link to my review:** https://github.com/NChyrklund/media-tracker-android/pull/11
**My Comment** https://github.com/NChyrklund/media-tracker-android/pull/11#pullrequestreview-4824954908

### What I Looked At
He had 4 files chaned, with two being the ones I looked at closely, his strings.xml and a one line change on MediaDetailScreen.kt was minimal, so it was looked at slightly.

The main files, WriteReviewScreen.kt and WriteReviewViewmodel.kt were the ones I looked at closely due to the new functionality it had added.

### What I Noticed
One thing I noticed is his LaunchedEffect when mediaId is loaded (init) or changed that loads the mediaId in the viewmodel, It grabs the mediaDetails, but this may be redundent as (assumption) if the WriteReview is being loaded by the MediaDetailScreen, then you can just pass the mediaData since its already been loaded. Due to the nature of this class and it being a first time for all of us, I left it out of the review comment as it might be wrong, or he might be implementing a different way around it.

I also saw a WriteReviewScreenPreview, and I'm not sure why its a class that has been made, it might be used for future impl for the class, or might be an artifact while he was working on this screen.

I left a note about the media cover with my explaination below;

### Comments I Left

*WriteReviewScreen.kt line 93*
``` markdown
Since this is also used by the MediaDetailViewModel, this can be put in a separate file/class instead of rewriting it here; it just saves time when you need to change the look of this section specifically, instead of finding all instances of it and editing it there.
```

*WriteReviewScreen.kt line 49*
``` markdown
Just to note, this will need to be hooked by NavGraph.kt if it's placed in there, or wherever, could potentially hijack onNavigateBack and have the same functionality, but could lead to future confusion.
```

*PR Message*
``` markdown
Left some comments about something that caught my eye, Just some small items that could change in the future depending on the way you continue working on the Reviews
```

---

## One Thing I Understood More Deeply
This week gave me a lot more freedom with implementing the UI and the Quotes how I wanted, and because of that, I naturally go back into the way I've done things before. In kotlin, (at least what the code check has been telling me), all functions should be in camelCase instead of PascalCase, and I gravitate towards PascalCase because in GoLang, PascalCase is for public while camelCase is for private vars and functions.

I also was researching and got pretty good at creating components like how I made the QuoteModal which is a small window that lets you create a quote.

Finally, the last two that I got really good at, is creating new APIs, By looking at the API docs that you gave on your site, I'm able to almost always figure out what to use with `@POST` `@Body` and such, although I keep forgetting to put `@Serialize` for my classes so it causes my program to crash.

I also explored outside of the required items to add a new section for quotes, so at the bottom, between Library and Connections lays Quotes where (currently only impl the self-quotes,) shows all quotes by user (self) and others (public), with a search and filters.

---

## One Thing I'm Still Confused About
Two things I'm still getting better at is
1. Forcing my brain to go from React style styling using something like sx={{width: 100%}} or style={{width: 100%}} and getting used to Kotlin's modifer = Modifier.fillMaxWidth() which is sometimes confusing me, as when I write something and I hit the 'flow' state, I subconsciously write style={{}} and have to remove it;


2. Dealing with Kotlin's (mainly Java's) imports, I'm much more used to having single imports where you import an entire package, something like Go's or JS's import XYZ and then XYZ.Z instead of having a whole 20+ lines of just imports like how Java has, its super annoying.

---

## Anything Else *(optional)*

I really like this week's work, and I feel like I'm getting the hang of all the stuff we've learned throughout the course so far, and I feel with more time just getting used to it, I could eventually get really good.

I just feel right now, unless you have specific needs, cross-platform development yields faster resaults, not better as you have to deal with abstraction, but one codebase working on both Apple and Android phones seams better for some projects - it all depends on the requirements tho.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
