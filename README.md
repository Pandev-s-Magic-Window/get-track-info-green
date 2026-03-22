✨ Part of the Magic Window series:

# Get Track Info (Green Edition)

Gets the track currently playing on your Spotify Desktop app and outputs it to stdout.

> [!IMPORTANT]  
> Please note that in order to get data from this program, you have to manually send a message to it through stdin. In other words, this program will not output data automatically on track changes.
> 
> If you are looking for that functionality, take a look at [get-track-info-core](https://github.com/Pandev-s-Magic-Window/get-track-info-core), which makes use of this project.

Pre-requisites
---

- Windows 10/11
- The [Spotify app](https://download.scdn.co/SpotifySetup.exe) for the Windows Desktop. 

> [!IMPORTANT]  
> The Spotify app from the Microsoft Store does WILL NOT work with this program

- Valid API keys (TOTP secrets) to communicate with the Spotify API (read down below for more info).

About API keys (TOTP secrets)
---
This program comes pre-bundled with the most recent credentials so you don't have to do anything on your part. However, please note that these credentials can become invalid at any time, so in that case you'll either need to:

- Wait for a new version of this program to be released, which will have the new credentials bundled within it.
- Provided new credentials yourself via configuration file (you'll have to find the credentials on your own). 

To add your own credentials, create a new file in `%appdata%/app.pandev.mw.get-track-info-green` named `api_keys.custom.toml` (take a look at `api_keys.default.toml` to see how you should format this file).

> [!IMPORTANT]  
> Do NOT modify `api_keys.default.toml`, since all changes to that file will be lost once you restart the program.

> [!TIP]  
> If you want your custom keys to always take precedence, use a high version number like 9999

Quick Start
---
Open a terminal and run the executable
```
.\mw-get-track-info-green.exe
```

To get data, just press enter. If there is a track playing on your Spotify, you'll get a message similar to this:

```
{
  "status": "ok",
  "track_id": "0E0DRHf5PfMeor0ZCwB3oT",
  "data": {
    "id": "0E0DRHf5PfMeor0ZCwB3oT",
    "name": "Otro Atardecer",
    "uri": "spotify:track:0E0DRHf5PfMeor0ZCwB3oT",
    "albumOfTrack": {
      "id": "3RQQmkQEvNCY4prGKE6oc5",
      "name": "Un Verano Sin Ti",
      "uri": "spotify:album:3RQQmkQEvNCY4prGKE6oc5"
    },
    "firstArtist": {
      "items": [
        {
          "id": "4q3ewBCX7sLwd24euuV69X",
          "uri": "spotify:artist:4q3ewBCX7sLwd24euuV69X",
          "profile": {
            "name": "Bad Bunny"
          }
        }
      ]
    },
    "otherArtists": {
      "items": [
        {
          "id": "2sSGPbdZJkaSE2AbcGOACx",
          "uri": "spotify:artist:2sSGPbdZJkaSE2AbcGOACx",
          "profile": {
            "name": "The Marías"
          }
        }
      ]
    }
  }
}
```

Credits
---
This program is a wrapper for [java-spotify-api](https://github.com/LabyStudio/java-spotify-api), and would not be possible without it.
