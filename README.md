# ☕ Coffee Order App

A native Android coffee ordering app built with **Kotlin** and **Firebase Realtime Database**, featuring category browsing, item detail, a shopping cart, and a warm brown UI theme.

---

## Features

- **Splash screen** — branded entry screen with a start button
- **Banner** — dynamic promotional banner loaded from Firebase
- **Category browser** — horizontal scrollable list with selection highlight
- **Popular items grid** — 2-column grid with image, title, price and short description
- **Item detail** — full image, description, rating, size selector, quantity picker, add-to-cart
- **Shopping cart** — full cart management with item count, subtotal, tax, delivery fee and total
- **Firebase Realtime Database** — all content (banners, categories, items) loaded remotely

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| UI | XML layouts + ViewBinding |
| Architecture | MVVM (ViewModel + LiveData) |
| Database | Firebase Realtime Database |
| Image loading | Glide |
| Local storage | TinyDB (SharedPreferences wrapper) |

---

## Project Structure

```
app/src/main/java/com/example/pizza2/
├── Activity/
│   ├── SplashActivity2.kt     # Entry splash screen
│   ├── MainActivity.kt        # Home — banner, categories, popular items
│   ├── ItemsListActivity.kt   # Category item list
│   ├── DetailActivity.kt      # Item detail + quantity + add to cart
│   └── CartActivity.kt        # Cart summary + checkout totals
├── Adapter/
│   ├── CategoryAdapter.kt     # Horizontal category chips
│   ├── PopularAdapter.kt      # Popular items grid
│   ├── ItemListCategoryAdapter.kt  # Category item grid
│   └── CartAdapter.kt         # Cart item list
├── Domain/
│   ├── BannerModel.kt         # Banner data class
│   ├── CategoryModel.kt       # Category data class
│   └── ItemsModel.kt          # Item data class (Serializable)
├── Helper/
│   ├── ManagmentCart.kt       # Cart logic (TinyDB-backed)
│   └── ChangeNumberItemsListener.kt  # Cart change callback
├── Repository/
│   └── MainRepository.kt      # Firebase data access layer
└── ViewModel/
    └── MainViewModel.kt       # ViewModel bridging UI and repository
```

---

## Getting Started

### Prerequisites

- Android Studio Hedgehog or newer
- A Firebase project with **Realtime Database** enabled

### Firebase Setup

1. Create a project at [console.firebase.google.com](https://console.firebase.google.com)
2. Enable **Realtime Database** and set read rules to allow access
3. Add the following top-level nodes to your database:

**Banner**
```json
{
  "0": { "url": "https://your-image-url.com/banner.jpg" }
}
```

**Category**
```json
{
  "0": { "id": 0, "title": "Cappuccino", "imagePath": "..." },
  "1": { "id": 1, "title": "Espresso",   "imagePath": "..." }
}
```

**Items**
```json
{
  "0": {
    "title": "Large Cappuccino",
    "description": "Rich espresso with steamed milk",
    "picUrl": ["https://your-image-url.com/item.jpg"],
    "price": 4.50,
    "rating": 4.8,
    "extra": "Best seller",
    "category": "0"
  }
}
```

4. Download `google-services.json` and place it in `app/`

### Run

Open the project in Android Studio and click **Run** (or `Shift+F10`).

---

## What Was Improved (Code Review)

| Issue | Fix |
|-------|-----|
| `observeForever` on all LiveData — memory leak, no lifecycle awareness | Replaced with `observe(this)` in all activities |
| Each `load*()` called twice — duplicate Firebase reads on every screen open | Removed all duplicate calls |
| `setBackgroundColor(R.drawable.*)` — drawable resource ID misused as a colour int | Changed to `setBackgroundResource(R.drawable.*)` |
| `TODO("not yet implemented")` in every `onCancelled` — crash on any Firebase error | Replaced with `Log.e(...)` error logging |
| `lateinit var binding` public in all activities | Made `private` in all activities and adapters |
| `getColor(R.color.*)` deprecated since API 23 | Replaced with `ContextCompat.getColor(context, R.color.*)` |
| `notifyDataSetChanged()` on every cart change — redraws entire list | Replaced with `notifyItemChanged` / `notifyItemRemoved` / `notifyItemRangeChanged` |
| Quantity starts at 0 in detail screen — user could add 0 items to cart | Initial quantity set to 1; minus button won't go below 1 |
| `"$" + price.toString()` string concatenation | Changed to Kotlin string templates `"$${item.price}"` |
| Banner access `it[0]` without null/empty check — crash on empty list | Added `if (it.isNotEmpty())` guard |
| Unused imports across multiple files | Removed all unused imports |
| `!!` on intent extras — crash if extras are missing | Replaced with safe `?: ""` defaults |
| `Math.round()` in Kotlin code | Replaced with Kotlin's `kotlin.math.round` |
