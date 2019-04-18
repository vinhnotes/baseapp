# BASE
[![](https://jitpack.io/v/whatsltd4us/baseapp.svg)](https://jitpack.io/#whatsltd4us/baseapp)\
Base using mvvm pattern using android data binding

---
# Install
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.whatsltd4us:baseapp:-SNAPSHOT'
	}
```
# Usage
- Add custom apllication 
```
<application
android:name="com.vtu.baseapp.BaseApplication"
...
```
- Add to recycleView item view
```
<data>
        <variable
            name="obj"
            type="com.vtu.baseapp.models.<your model>" />
    </data>
```
- Extends BaseRecycleViewFragment
```
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
...
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ViewDataBinding binding = ViewDataBinding.inflate(this.getLayoutInflater());
        binding.setViewModel((<your viewmodel>) viewModel);
        return view;
```

# Credit
- https://github.com/XRecyclerView/XRecyclerView
