# OpenAI Moderation Spigot Plugin
### Spigot chat moderation Plugin based on [OpenAI](https://openai.com/)

### Requirements ⚙️
- OpenAI Account
- OpenAI API key, you can get yours for free [here](https://beta.openai.com/account/api-keys)

Once you got your api key place that into the config.yml file,
without a valid api key, the plugin won't enable.

### OpenAI API Moderation Endpoint 🛡️
This plugin uses the [moderation endpoint](https://beta.openai.com/docs/guides/moderation/overview)
of the OpenAI API. 

In the config.yml file you can choose between two models: `stable` and `latest` as well as configure
individual thresholds for each different category of speech it detects.
