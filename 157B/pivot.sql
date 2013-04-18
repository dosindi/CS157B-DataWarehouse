SELECT category as Quantity, 
	(SELECT sum(quantity) 
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND state = "California"
			AND P.category = category
	) as "California",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND state = "New York"
			AND P.category = category
	)as "New York"
	FROM forum.product P
	GROUP BY category;