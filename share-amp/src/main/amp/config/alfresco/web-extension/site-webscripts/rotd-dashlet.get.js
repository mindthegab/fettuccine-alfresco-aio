
function main()
{

	var connector = remote.connect("alfresco");
	var result = connector.get("/recipe/rotd");
	var recipe = jsonUtils.toObject(result); 
	model['title'] = recipe["title"];
	model['instructions'] = recipe["instructions"];
	model['recipeUrl'] = recipe["url"];


	// Dashlet widgets
	var widgets = [];
	// Title bar actions
	var actions = [];
	actions.push({
	cssClass: "help",
	bubbleOnClick:
	{
	message: msg.get("dashlet.help")
	},
	tooltip: msg.get("dashlet.help.tooltip")
	});
	widgets.push({
	id : "DashletTitleBarActions",
	name : "Alfresco.widget.DashletTitleBarActions",
	useMessages : false,
	options : {
	actions: actions
	}
	});
	
	model.widgets = widgets;
}

main();