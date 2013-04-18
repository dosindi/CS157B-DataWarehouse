SELECT state as Quantity, 
	(SELECT sum(quantity) 
		FROM forum.quantity, forum.store, forum.date
		WHERE storeID = store.id
			AND dateID = date.id
			AND years = "2010"
			AND S.state = state
	) as "2010",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.date
		WHERE storeID = store.id
			AND dateID = date.id
			AND years = "2011"
			AND S.state = state
	)as "2011",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.date
		WHERE storeID = store.id
			AND dateID = date.id
			AND years = "2012"
			AND S.state = state
	)as "2012",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.date
		WHERE storeID = store.id
			AND dateID = date.id
			AND years = "2013"
			AND S.state = state
	)as "2013"
	FROM forum.Store S
	GROUP BY state;