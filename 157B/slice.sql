SELECT state as Quantity, 
	(SELECT sum(quantity) 
		FROM forum.quantity, forum.store, forum.product, forum.date
		WHERE storeID = store.id
			AND productID = product.id
			AND dateID = date.id
			AND category = "Individual Sports"
			AND S.state = state
			AND years = "2013"
	) as "Individual Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.product, forum.date
		WHERE storeID = store.id
			AND productID = product.id
			AND dateID = date.id
			AND category = "Team Sports"
			AND S.state = state
			AND years = "2013"
	)as "Team Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.product, forum.date
		WHERE storeID = store.id
			AND productID = product.id
			AND dateID = date.id
			AND category = "Water Sports"
			AND S.state = state
			AND years = "2013"
	)as "Water Sports"
	FROM forum.Store S
	GROUP BY state;