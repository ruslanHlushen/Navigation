# Navigation

This library created for easy work with navigation and back stack in android application.

add to progect build.gradle :
repositories {
        maven {
            url "http://dl.bintray.com/ruslanhlushen0808/maven"
        }
    }
    
and compile 'com.github.ruslanHlushen:android_app_navigation:0.0.5'   to  app build.gradle


Paramethers:
String fragmentNameForBackStack - some tag to get fragment from backStack; 
Object data - some data to fragment (you can use newInstance(data) cast data to bundle and setArguments()); 
boolean useAddTransaction - if true - will be use add methdod of FragmentTransaction, but if false - replace. ;

Methods:
 Add new fragment
<h2>void startFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction);</h2>

 Add many fragments if useAddTransaction = true, then onCreate(),onCreateView(),onViewCreated() of all fragment will be called in course, if useAddTransaction = false only onCreate() of all fragment will be called in course, and onCreateView(),onViewCreated() only of last fragment.
All fragments will be added to backStack, so onBackPressed() you return to previous.
void startFragmentWithBackStack(List<DataModel> dataModelList, boolean useAddTransaction);

 Remove all fragments of backStack, that are after position(stars from 0) and fragment that is at position and set instead of it fragment that you want.
<h2>void startFragmentAndSetAtBackStackPosition(String fragmentNameForBackStack, int position, Object data, boolean useAddTransaction);</h2>

 Works like previous method, but set position after fragment with fragmentNameToSetAfter name in backStack.
<h2>void startFragmentAndSetAfter(String fragmentNameForBackStack,
                                  String fragmentNameToSetAfter,
                                  boolean clearBackStackIfWouldntFind,
                                  Object data,
                                  boolean useAddTransaction);</h2>
                                  
Works like previous method, but set at position instead of fragment with fragmentNameToSetBefore name in backStack.
<h2>void startFragmentAndSetBefore(String fragmentNameForBackStack,
                                   String fragmentNameToSetBefore,
                                   boolean clearBackStackIfWouldntFind,
                                   Object data,
                                   boolean useAddTransaction);</h2>

 Remove previous fragment of backStack, and set instead of it fragment that you want.
<h2>void changeOnlyCurrentFragment(String fragmentNameForBackStack, Object data, boolean useAddTransaction);</h2>

//
<h2>void returnToPreviousFragment();</h2>

 Remove all fragmets and stop at someone, that will be dislay.
<h2>void returnToFragment(String fragmentNameForBackStack);</h2>

 Works like previous method, but you call send some data to fragment.
<h2>void returnToFragmentWithResult(String fragmentNameForBackStack, Object data);</h2>

 If in back stack is one element
<h2>void onExit();</h2>
