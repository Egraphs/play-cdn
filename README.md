This is your new Play 2.0 application
=====================================

First, I want to thank James Ward for this code which I largely used for this module.  Specifically, this implementation is inspired by this blog: http://www.jamesward.com/2012/08/08/edge-caching-with-play2-heroku-cloudfront

With this 'module' you can get CDNs working easy.

1. Add the dependency on this module.

2. Add a value to your conf/application.conf file that points to your cdn.

```
cdn.contenturl="XXXXXXXXXXXXXX.cloudfront.net
```

By default the cdn assets will use HTTPS.  If you don't want to use HTTPS, add this setting cdn.secure=false.

3. Replace your Assets route in your conf/routes file like this.

If you had:

```
GET     /assets/*file               controllers.Assets.at(path="/public", file)
```

You now need

```
GET     /assets/*file               controllers.RemoteAssets.at(path="/public", file)
```

4. Replace all "routes.Assets.at" with "RemoteAssets.at" in your project.  (note, you no longer need the "routes." because you are no longer using the reverse router.)
