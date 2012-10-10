This is your new Play 2.0 application
=====================================

First, I want to thank James Ward for this code which I largely used for this module.  Specifically, this implementation is inspired by this blog: http://www.jamesward.com/2012/08/08/edge-caching-with-play2-heroku-cloudfront

With this 'module' you can get CDNs working easy.

1. Add the dependency on this module.

2. Add a value to your conf/application.conf file that points to your cdn.

```
cdn.contenturl="https://XXXXXXXXXXXXXX.cloudfront.net
```

3. Replace your Assets route in your conf/routes file like this.

If you had:

```
GET     /assets/*file               controllers.Assets.at(path="/public", file)
```

You now need

```
GET     /assets/*file               controllers.RemoteAssets.at(path="/public", file)
```

4. Replace all "Assets.at" with "RemoteAssets.at" in your project.
