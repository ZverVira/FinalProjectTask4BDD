Scenario Outline: it is possible to hide event on Public API for <sport> on daily endpoint
Given not hidden event for <sport> on Public API DAILY endpoint
When event becomes hidden
Then MTA produces EVENT message with key {internalMatchId}
And event is hidden in EVENT message
And MASTER produces API_EVENTS message with key {internalMatchId}
And event is hidden in API_EVENT message
And event is not available on Public Api DAILY endpoint for app, web and react routs
Examples:
| sport      |
| SOCCER     |
| TENNIS     |
| ICE_HOCKEY |
| BASKETBALL |
| CRICKET    |

@Given("^not hidden event for (.+) on Public API DAILY endpoint$")
public void notHiddenEventForSportOnDailyEndpoint(Sports sport) {
testContext.addValue(GeneralContext.SPORT, sport);

List<EventDto> visibleEvents = publicApiService.getEventsOnDailyEndpoint(sport, PublicApiRoutes.APP)
.stream()
.filter(event -> event.getStage().isVisible())
.filter(EventDto::isVisible)
.toList();

assertFalse(visibleEvents.isEmpty(), "There is no visible event on DAILY endpoint for sport " + sport);

EventDto event = visibleEvents.get(RandomUtils.nextInt(0, visibleEvents.size() - 1));

log.info("Got event {} on DAILY for sport {}", event.getInternalMatchId(), sport);
testContext.addValue(GeneralContext.EVENT_ENET, event);
}

Scenario Outline: it is possible to hide event on Favorites API for <sport> on GET my matches endpoint
Given not hidden event for <sport> on Public API DAILY endpoint
When event becomes hidden
Then MTA produces EVENT message with key provider
And event is hidden in EVENT message
And MASTER produces API_EVENTS message with key {internalMatchId}
And event is hidden in API_EVENT message
And event is not visible on Favorites Api GET MY_MATCHES endpoint for app, web and react routs
Examples:
| sport      | provider|
| SOCCER     |ENET     |
| TENNIS     |ENET     |
| ICE_HOCKEY |ENET     |
| BASKETBALL |ENET     |
| CRICKET    |ENET     |


