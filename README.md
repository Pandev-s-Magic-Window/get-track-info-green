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
  "request_id": "YOUR_REQUEST_ID",
  "data": {
    "track_id": "0E0DRHf5PfMeor0ZCwB3oT",
    "track_name": "Otro Atardecer",
    "track_duration_ms": 244444,
    "track_is_explicit": false,
    "album_id": "3RQQmkQEvNCY4prGKE6oc5",
    "album_name": "Un Verano Sin Ti",
    "artist_name": "Bad Bunny",
    "artist_full_name": "Bad Bunny, The Marías"
    "extra_data": {Optional Data from GraphSQ endpoint "getTrack"}
  }
}
```

To pass an optional request ID you need to input a message like this one: `{"request_id": "YOUR_REQUEST_ID"}"`

<details>
  <summary>Click here to see a sample response from GraphQL endpoint "getTrack"</summary>

```
{
  "data": {
    "trackUnion": {
      "__typename": "Track",
      "associationsV3": {
        "audioAssociations": {
          "__typename": "TrackAudioAssociationPage",
          "items": []
        },
        "videoAssociations": {
          "totalCount": 0
        }
      },
      "contentRating": {
        "label": "NONE"
      },
      "duration": {
        "totalMilliseconds": 244444
      },
      "id": "0E0DRHf5PfMeor0ZCwB3oT",
      "mediaType": "AUDIO",
      "name": "Otro Atardecer",
      "playability": {
        "playable": true,
        "reason": "PLAYABLE"
      },
      "playcount": "719540735",
      "saved": false,
      "sharingInfo": {
        "shareId": "aTnLtkF8QRqU59kZBhLJaQ",
        "shareUrl": "https://open.spotify.com/track/0E0DRHf5PfMeor0ZCwB3oT?si=aTnLtkF8QRqU59kZBhLJaQ"
      },
      "trackNumber": 17,
      "uri": "spotify:track:0E0DRHf5PfMeor0ZCwB3oT",
      "visualIdentity": {
        "squareCoverImage": {
          "__typename": "VisualIdentityImage",
          "extractedColorSet": {
            "encoreBaseSetTextColor": {
              "alpha": 255,
              "blue": 98,
              "green": 165,
              "red": 255
            },
            "highContrast": {
              "backgroundBase": {
                "alpha": 255,
                "blue": 0,
                "green": 55,
                "red": 139
              },
              "backgroundTintedBase": {
                "alpha": 255,
                "blue": 0,
                "green": 20,
                "red": 94
              },
              "textBase": {
                "alpha": 255,
                "blue": 255,
                "green": 255,
                "red": 255
              },
              "textBrightAccent": {
                "alpha": 255,
                "blue": 255,
                "green": 255,
                "red": 255
              },
              "textSubdued": {
                "alpha": 255,
                "blue": 148,
                "green": 192,
                "red": 255
              }
            },
            "higherContrast": {
              "backgroundBase": {
                "alpha": 255,
                "blue": 0,
                "green": 21,
                "red": 98
              },
              "backgroundTintedBase": {
                "alpha": 255,
                "blue": 40,
                "green": 61,
                "red": 140
              },
              "textBase": {
                "alpha": 255,
                "blue": 255,
                "green": 255,
                "red": 255
              },
              "textBrightAccent": {
                "alpha": 255,
                "blue": 96,
                "green": 215,
                "red": 30
              },
              "textSubdued": {
                "alpha": 255,
                "blue": 148,
                "green": 192,
                "red": 255
              }
            },
            "minContrast": {
              "backgroundBase": {
                "alpha": 255,
                "blue": 51,
                "green": 125,
                "red": 227
              },
              "backgroundTintedBase": {
                "alpha": 255,
                "blue": 11,
                "green": 100,
                "red": 199
              },
              "textBase": {
                "alpha": 255,
                "blue": 255,
                "green": 255,
                "red": 255
              },
              "textBrightAccent": {
                "alpha": 255,
                "blue": 255,
                "green": 255,
                "red": 255
              },
              "textSubdued": {
                "alpha": 255,
                "blue": 255,
                "green": 255,
                "red": 255
              }
            }
          }
        }
      },
      "albumOfTrack": {
        "copyright": {
          "items": [
            {
              "text": "(C) 2022 Rimas Entertainment",
              "type": "C"
            },
            {
              "text": "(P) 2022 Rimas Entertainment",
              "type": "P"
            }
          ],
          "totalCount": 2
        },
        "courtesyLine": "",
        "id": "3RQQmkQEvNCY4prGKE6oc5",
        "date": {
          "isoString": "2022-05-06T00:00:00Z",
          "precision": "DAY",
          "year": 2022
        },
        "name": "Un Verano Sin Ti",
        "playability": {
          "playable": true
        },
        "sharingInfo": {
          "shareId": "dF92TtTESDOpWm4VfETPvg",
          "shareUrl": "https://open.spotify.com/album/3RQQmkQEvNCY4prGKE6oc5?si=dF92TtTESDOpWm4VfETPvg"
        },
        "tracks": {
          "items": [
            {
              "track": {
                "trackNumber": 1,
                "uri": "spotify:track:6Xom58OOXk2SoU711L2IXO"
              }
            },
            {
              "track": {
                "trackNumber": 2,
                "uri": "spotify:track:1dm6z1fWB0cErMszU25dy2"
              }
            },
            {
              "track": {
                "trackNumber": 3,
                "uri": "spotify:track:6Sq7ltF9Qa7SNFBsV5Cogx"
              }
            },
            {
              "track": {
                "trackNumber": 4,
                "uri": "spotify:track:1IHWl5LamUGEuP4ozKQSXZ"
              }
            },
            {
              "track": {
                "trackNumber": 5,
                "uri": "spotify:track:5CzixCxDkRXX9mScCmah8O"
              }
            },
            {
              "track": {
                "trackNumber": 6,
                "uri": "spotify:track:5kVZxyMY1gfdCLiChsfjn1"
              }
            },
            {
              "track": {
                "trackNumber": 7,
                "uri": "spotify:track:41oY4WCTj5kccfesTVFnvN"
              }
            },
            {
              "track": {
                "trackNumber": 8,
                "uri": "spotify:track:31i56LZnwE6uSu3exoHjtB"
              }
            },
            {
              "track": {
                "trackNumber": 9,
                "uri": "spotify:track:1797zYiX4cKosMH836X9Gt"
              }
            },
            {
              "track": {
                "trackNumber": 10,
                "uri": "spotify:track:5Eax0qFko2dh7Rl2lYs3bx"
              }
            },
            {
              "track": {
                "trackNumber": 11,
                "uri": "spotify:track:4tYFy8ALRjIZvnvSLw5lxN"
              }
            },
            {
              "track": {
                "trackNumber": 12,
                "uri": "spotify:track:2uflssWlCaJ6CbTMOsUpNI"
              }
            },
            {
              "track": {
                "trackNumber": 13,
                "uri": "spotify:track:2NMjggapJcXXM7WccGEBUO"
              }
            },
            {
              "track": {
                "trackNumber": 14,
                "uri": "spotify:track:3k3NWokhRRkEPhCzPmV8TW"
              }
            },
            {
              "track": {
                "trackNumber": 15,
                "uri": "spotify:track:4d4ZXH4dr5bYfgErHiZCX2"
              }
            },
            {
              "track": {
                "trackNumber": 16,
                "uri": "spotify:track:0UvZcEfpzVyx47QsRbjyBz"
              }
            },
            {
              "track": {
                "trackNumber": 17,
                "uri": "spotify:track:0E0DRHf5PfMeor0ZCwB3oT"
              }
            },
            {
              "track": {
                "trackNumber": 18,
                "uri": "spotify:track:6VrQTLzzuyGIYjUDe4kAZk"
              }
            },
            {
              "track": {
                "trackNumber": 19,
                "uri": "spotify:track:44XjoNvtwevktFKjvVe1vH"
              }
            },
            {
              "track": {
                "trackNumber": 20,
                "uri": "spotify:track:28GCbKgjlVD9eDmasGbe0T"
              }
            },
            {
              "track": {
                "trackNumber": 21,
                "uri": "spotify:track:18HOjcvTVV7W8YzBuOsAPS"
              }
            },
            {
              "track": {
                "trackNumber": 22,
                "uri": "spotify:track:0wihfILRNOwE2156Shezc8"
              }
            },
            {
              "track": {
                "trackNumber": 23,
                "uri": "spotify:track:71wFwRo8xGc4lrcyKwsvba"
              }
            }
          ],
          "totalCount": 23
        },
        "type": "ALBUM",
        "uri": "spotify:album:3RQQmkQEvNCY4prGKE6oc5",
        "coverArt": {
          "extractedColors": {
            "colorRaw": {
              "hex": "#F89048"
            }
          },
          "sources": [
            {
              "height": 300,
              "url": "https://i.scdn.co/image/ab67616d00001e0249d694203245f241a1bcaa72",
              "width": 300
            },
            {
              "height": 64,
              "url": "https://i.scdn.co/image/ab67616d0000485149d694203245f241a1bcaa72",
              "width": 64
            },
            {
              "height": 640,
              "url": "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72",
              "width": 640
            }
          ]
        }
      },
      "firstArtist": {
        "items": [],
        "totalCount": 2
      },
      "otherArtists": {
        "items": [
          {
            "discography": {
              "popularReleasesAlbums": {
                "items": [
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#284880"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e028aa339341a0b0c813909c831",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d000048518aa339341a0b0c813909c831",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b2738aa339341a0b0c813909c831",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2024-05-31T00:00:00Z",
                      "precision": "DAY",
                      "year": 2024
                    },
                    "name": "Submarine",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "Q4bbwkbHTIKP1zxRyUsrsA",
                      "shareUrl": "https://open.spotify.com/album/03guxdOi12XJbnvxvxbpwG?si=Q4bbwkbHTIKP1zxRyUsrsA"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:3zJakbnIw0ufiVnIFkLfA1"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 2,
                            "uri": "spotify:track:1jlBePA3tcycCoANSSZbgj"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 3,
                            "uri": "spotify:track:3SH1vOTwgg5Ma1NhHaXvrA"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 4,
                            "uri": "spotify:track:19fKJrO9XdOf6Xla2QHecO"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 5,
                            "uri": "spotify:track:3HVRZxCp3BWYuYe8L8BMNH"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 6,
                            "uri": "spotify:track:4yj9o2kdVcZfT57IALrsZA"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 7,
                            "uri": "spotify:track:4gtIYSYu8u2ItBqrhCaChL"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 8,
                            "uri": "spotify:track:0phzfJn8NeT1LkbqfV2peI"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 9,
                            "uri": "spotify:track:3vxvz0JoRDvnx2jG9oPljA"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 10,
                            "uri": "spotify:track:3L0JDlycMhSA7R0M0OfNIY"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 11,
                            "uri": "spotify:track:3siwsiaEoU4Kuuc9WKMUy5"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 12,
                            "uri": "spotify:track:1N2NENvgrnemh4AuV2hawv"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 13,
                            "uri": "spotify:track:7eQ0SV1eQXSZHBA1x4mcYu"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 14,
                            "uri": "spotify:track:0InIeZW4P6VO7dUGRM4AKH"
                          }
                        }
                      ],
                      "totalCount": 14
                    },
                    "type": "ALBUM",
                    "uri": "spotify:album:03guxdOi12XJbnvxvxbpwG"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#480808"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e02657d6776f64aa731c8d1748b",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d00004851657d6776f64aa731c8d1748b",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b273657d6776f64aa731c8d1748b",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2021-06-25T00:00:00Z",
                      "precision": "DAY",
                      "year": 2021
                    },
                    "name": "CINEMA",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "eIouvG9uSQu3Kf9pwiYVfQ",
                      "shareUrl": "https://open.spotify.com/album/5TkaDC4mYSLBvdG6UrIB0v?si=eIouvG9uSQu3Kf9pwiYVfQ"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:0ZjTXCk5QKDR0wGGPgqSS3"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 2,
                            "uri": "spotify:track:5WVWQQpBJqljbZtxo19CxS"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 3,
                            "uri": "spotify:track:4zXZ5Mq2L6jnsOsTssgRh8"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 4,
                            "uri": "spotify:track:410rGaFDJPwjsr9m9RPCkz"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 5,
                            "uri": "spotify:track:1HJxrABnErE3PIfil5NqIJ"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 6,
                            "uri": "spotify:track:3EzlgdxyFvjeAGzR5WxHB8"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 7,
                            "uri": "spotify:track:1ShRHPAiiIrh0arZbSFmx1"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 8,
                            "uri": "spotify:track:5dFG12OQz10CbUFZIYH0gv"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 9,
                            "uri": "spotify:track:3WOZcIibmurJult4Z7Wfce"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 10,
                            "uri": "spotify:track:3Mqx9OWTEhBEeOEuUDklei"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 11,
                            "uri": "spotify:track:76vbQVnCEHe02OP4EWvApz"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 12,
                            "uri": "spotify:track:4raL05JS5aNmXwClc2ATA8"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 13,
                            "uri": "spotify:track:0ZpgDIYEJ7H2g8zkIEDQJz"
                          }
                        }
                      ],
                      "totalCount": 13
                    },
                    "type": "ALBUM",
                    "uri": "spotify:album:5TkaDC4mYSLBvdG6UrIB0v"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#4088B8"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e0258c0f15c5247c0b719d7ffb0",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d0000485158c0f15c5247c0b719d7ffb0",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b27358c0f15c5247c0b719d7ffb0",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2024-11-15T00:00:00Z",
                      "precision": "DAY",
                      "year": 2024
                    },
                    "name": "No One Noticed (Extended)",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "skhj06UVT3GovS0qEtiJjw",
                      "shareUrl": "https://open.spotify.com/album/6FkCugnh8o4VjxMMw4xlRC?si=skhj06UVT3GovS0qEtiJjw"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:4Q9qkdqu0qKKjaftRrAK9C"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 2,
                            "uri": "spotify:track:2kn0fLwt9QRorHuGdmk6ze"
                          }
                        }
                      ],
                      "totalCount": 2
                    },
                    "type": "SINGLE",
                    "uri": "spotify:album:6FkCugnh8o4VjxMMw4xlRC"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#535353"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e02a66ab2cbaebcc84ddd45a078",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d00004851a66ab2cbaebcc84ddd45a078",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b273a66ab2cbaebcc84ddd45a078",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2025-04-04T00:00:00Z",
                      "precision": "DAY",
                      "year": 2025
                    },
                    "name": "Back To Me",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "4G0lPkXzQe6E0O3UNGU-Nw",
                      "shareUrl": "https://open.spotify.com/album/3y8LtE30zPfQ4sXYRKvt2e?si=4G0lPkXzQe6E0O3UNGU-Nw"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:4E0P1xs3JNmsNr5c5nFTZJ"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 2,
                            "uri": "spotify:track:4pwJ6OujVQL3IpCr8KeXIg"
                          }
                        }
                      ],
                      "totalCount": 2
                    },
                    "type": "SINGLE",
                    "uri": "spotify:album:3y8LtE30zPfQ4sXYRKvt2e"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#535353"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e024076c62eeace86cc7bde7aef",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d000048514076c62eeace86cc7bde7aef",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b2734076c62eeace86cc7bde7aef",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2025-04-02T00:00:00Z",
                      "precision": "DAY",
                      "year": 2025
                    },
                    "name": "Nobody New",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "MJ1Wf7CWQ9moyBbHqbRU8w",
                      "shareUrl": "https://open.spotify.com/album/0CHEK7iHmeB7bZ8lqAsbS3?si=MJ1Wf7CWQ9moyBbHqbRU8w"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:3BUc2A0MJ53FHwiy3eohBt"
                          }
                        }
                      ],
                      "totalCount": 1
                    },
                    "type": "SINGLE",
                    "uri": "spotify:album:0CHEK7iHmeB7bZ8lqAsbS3"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#882828"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e02a64b06c0eb30ce15c3e6edc1",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d00004851a64b06c0eb30ce15c3e6edc1",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b273a64b06c0eb30ce15c3e6edc1",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2018-09-28T00:00:00Z",
                      "precision": "DAY",
                      "year": 2018
                    },
                    "name": "Superclean, Vol. II",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "7fmU2cHqT56rzooO4Mj5rw",
                      "shareUrl": "https://open.spotify.com/album/1Iu5sceGmML4CeQ2f5Q6aO?si=7fmU2cHqT56rzooO4Mj5rw"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:1mbuSss0OVqfS00y7yCNCZ"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 2,
                            "uri": "spotify:track:16gJmoOqRt2lKmlXtDpWgH"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 3,
                            "uri": "spotify:track:5P9PQR7CM5YPEU2I3EypWM"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 4,
                            "uri": "spotify:track:3XCpEFU4uXsBq5WmVQQKC9"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 5,
                            "uri": "spotify:track:02wNtFxlr9dLmZXKT5TIF5"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 6,
                            "uri": "spotify:track:4pnXcQSeFwrr3GPHEmeS7m"
                          }
                        }
                      ],
                      "totalCount": 6
                    },
                    "type": "EP",
                    "uri": "spotify:album:1Iu5sceGmML4CeQ2f5Q6aO"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#D85850"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e020924b9c6232d2348d4efa0dc",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d000048510924b9c6232d2348d4efa0dc",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b2730924b9c6232d2348d4efa0dc",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2017-11-03T00:00:00Z",
                      "precision": "DAY",
                      "year": 2017
                    },
                    "name": "Superclean, Vol. I",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "IjgZYdUjQECdG8JnJ7eWLg",
                      "shareUrl": "https://open.spotify.com/album/5XX1xVOP61GDQCGaZMLjhO?si=IjgZYdUjQECdG8JnJ7eWLg"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:06cqIVC8kRAT02qfHQT65v"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 2,
                            "uri": "spotify:track:4CUTUsBGjHslu2Bd3AOXGg"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 3,
                            "uri": "spotify:track:3Vm6yiTPfhFeUHD4gt42cZ"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 4,
                            "uri": "spotify:track:190IqlryWu91WBKeDgZqZz"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 5,
                            "uri": "spotify:track:0hxl37ZV9GgcY1Pn8UiPWK"
                          }
                        },
                        {
                          "track": {
                            "trackNumber": 6,
                            "uri": "spotify:track:4eevohYu5jHXgnCitivUVT"
                          }
                        }
                      ],
                      "totalCount": 6
                    },
                    "type": "EP",
                    "uri": "spotify:album:5XX1xVOP61GDQCGaZMLjhO"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#485068"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e0214a012da5638f895eaefffa5",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d0000485114a012da5638f895eaefffa5",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b27314a012da5638f895eaefffa5",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2023-03-10T00:00:00Z",
                      "precision": "DAY",
                      "year": 2023
                    },
                    "name": "Si Me Voy (with The Marías)",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "g3YIgGZESauGAYVsWLhgzg",
                      "shareUrl": "https://open.spotify.com/album/5TZm9qi0223t20ypmJevEq?si=g3YIgGZESauGAYVsWLhgzg"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:6GiCszfL4D2GlCU8tFU3sR"
                          }
                        }
                      ],
                      "totalCount": 1
                    },
                    "type": "SINGLE",
                    "uri": "spotify:album:5TZm9qi0223t20ypmJevEq"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#C0B0B8"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e02444c0e8a719691aded9e08b7",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d00004851444c0e8a719691aded9e08b7",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b273444c0e8a719691aded9e08b7",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2020-10-16T00:00:00Z",
                      "precision": "DAY",
                      "year": 2020
                    },
                    "name": "Care For You",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "qnXZ3ULNRVagxZX-KH9FPA",
                      "shareUrl": "https://open.spotify.com/album/4ENFZsf6OIKxck9I6gUwrk?si=qnXZ3ULNRVagxZX-KH9FPA"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:2CBtdZVcpSwaxOcLUi1AGo"
                          }
                        }
                      ],
                      "totalCount": 1
                    },
                    "type": "SINGLE",
                    "uri": "spotify:album:4ENFZsf6OIKxck9I6gUwrk"
                  },
                  {
                    "coverArt": {
                      "extractedColors": {
                        "colorRaw": {
                          "hex": "#C04008"
                        }
                      },
                      "sources": [
                        {
                          "height": 300,
                          "url": "https://i.scdn.co/image/ab67616d00001e0243b856d202c62ba161edc1b7",
                          "width": 300
                        },
                        {
                          "height": 64,
                          "url": "https://i.scdn.co/image/ab67616d0000485143b856d202c62ba161edc1b7",
                          "width": 64
                        },
                        {
                          "height": 640,
                          "url": "https://i.scdn.co/image/ab67616d0000b27343b856d202c62ba161edc1b7",
                          "width": 640
                        }
                      ]
                    },
                    "date": {
                      "isoString": "2020-03-20T00:00:00Z",
                      "precision": "DAY",
                      "year": 2020
                    },
                    "name": "Jupiter",
                    "playability": {
                      "playable": true
                    },
                    "sharingInfo": {
                      "shareId": "GgytzO3CTRWW2u3vXsFK2w",
                      "shareUrl": "https://open.spotify.com/album/3U7jdirSqKYriJCSIucl10?si=GgytzO3CTRWW2u3vXsFK2w"
                    },
                    "tracks": {
                      "items": [
                        {
                          "track": {
                            "trackNumber": 1,
                            "uri": "spotify:track:2EPuv7zyLpFqReJoYrIFzm"
                          }
                        }
                      ],
                      "totalCount": 1
                    },
                    "type": "SINGLE",
                    "uri": "spotify:album:3U7jdirSqKYriJCSIucl10"
                  }
                ],
                "totalCount": 18
              }
            },
            "id": "2sSGPbdZJkaSE2AbcGOACx",
            "profile": {
              "name": "The Marías"
            },
            "uri": "spotify:artist:2sSGPbdZJkaSE2AbcGOACx",
            "visuals": {
              "avatarImage": {
                "sources": [
                  {
                    "height": 640,
                    "url": "https://i.scdn.co/image/ab6761610000e5ebaf586afa2b397f1288683a76",
                    "width": 640
                  },
                  {
                    "height": 160,
                    "url": "https://i.scdn.co/image/ab6761610000f178af586afa2b397f1288683a76",
                    "width": 160
                  },
                  {
                    "height": 320,
                    "url": "https://i.scdn.co/image/ab67616100005174af586afa2b397f1288683a76",
                    "width": 320
                  }
                ]
              }
            }
          }
        ]
      }
    }
  }
}
```
</details>

Credits
---
This program is a wrapper for [java-spotify-api](https://github.com/LabyStudio/java-spotify-api), and would not be possible without it.
